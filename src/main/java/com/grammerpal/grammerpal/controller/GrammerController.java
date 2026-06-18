package com.grammerpal.grammerpal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grammerpal.grammerpal.model.CorrectionRequest;
import com.grammerpal.grammerpal.model.CorrectionResponse;
import com.grammerpal.grammerpal.service.GeminiService;
import com.grammerpal.grammerpal.service.GrammerService;
import com.grammerpal.grammerpal.entity.CorrectionHistory;
import com.grammerpal.grammerpal.repository.CorrectionHistoryRepository;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrammerController {

    @PostMapping("/correct")
    public CorrectionResponse correct(@RequestBody CorrectionRequest request) throws Exception
    {
        String result =
                geminiService.correctText(request.getText());

        String corrected = "";
        String explanation = "";

        String[] parts = result.split("Explanation:", 2);

        if(parts.length == 2) {

            corrected = parts[0]
                    .replace("Corrected:", "")
                    .trim();

            explanation = parts[1].trim();
        }

        CorrectionHistory history =
                new CorrectionHistory(
                        request.getText(),
                        corrected,
                        explanation,
                        LocalDateTime.now()
                );

        repository.save(history);

        return new CorrectionResponse(
                corrected,
                explanation
        );
    }
    private final GeminiService geminiService;
    private final GrammerService grammerService;
    private final CorrectionHistoryRepository repository;

    public GrammerController(GrammerService grammerService, GeminiService geminiService, CorrectionHistoryRepository repository)
    {
        this.grammerService=grammerService;
        this.geminiService=geminiService;
        this.repository = repository;
    }

    @GetMapping("/apikey")
    public String apiKey()
    {
        return geminiService.getApiKey();
    }

    @GetMapping("/gemini")
    public String geminiTest() throws JsonProcessingException {
        return geminiService.correctText("I goes to market yesterday");
    }
}
