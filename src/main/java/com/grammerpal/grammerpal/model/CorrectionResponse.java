package com.grammerpal.grammerpal.model;

public class CorrectionResponse {

    private String corrected;
    private String explanation;
    public CorrectionResponse(String corrected,String explanation)
    {
        this.explanation=explanation;
        this.corrected=corrected;
    }
    public String getCorrected()
    {
        return corrected;
    }
    public void setCorrected(String corrected)
    {
        this.corrected=corrected;
    }
    public String getExplanation()
    {
        return explanation;
    }
    public void setExplanation(String explanation)
    {
        this.explanation=explanation;
    }
}
