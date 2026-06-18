package com.grammerpal.grammerpal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public String getApiKey()
    {
        return apiKey;
    }

    public String correctText(String text) throws JsonProcessingException {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-goog-api-key", apiKey);

        String body = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "Correct the grammar of this sentence.
                
                                  Return the result in this format:
                
                                  Corrected: <corrected sentence>
                
                                  Explanation: <short explanation>
                
                                  Sentence: %s"
                }
              ]
            }
          ]
        }
        """.formatted(text);

        HttpEntity<String> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        String.class
                );

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        String correctedText =
                root.path("candidates")
                        .get(0)
                        .path("content")
                        .path("parts")
                        .get(0)
                        .path("text")
                        .asText();

        return correctedText;
    }
}
