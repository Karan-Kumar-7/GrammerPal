package com.grammerpal.grammerpal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "corrections")
public class CorrectionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalText;

    @Column(columnDefinition = "TEXT")
    private String correctedText;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private LocalDateTime createdAt;

    public CorrectionHistory() {
    }

    public CorrectionHistory(String originalText,
                             String correctedText,
                             String explanation,
                             LocalDateTime createdAt) {
        this.originalText = originalText;
        this.correctedText = correctedText;
        this.explanation = explanation;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }

    public String getOriginalText() { return originalText; }
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getCorrectedText() { return correctedText; }
    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}