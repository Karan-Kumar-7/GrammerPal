package com.grammerpal.grammerpal.service;

import org.springframework.stereotype.Service;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import java.util.List;
import org.languagetool.tools.Tools;

@Service
public class GrammerService {
    public String correctText(String text) throws Exception
    {
        JLanguageTool tool=new JLanguageTool(new AmericanEnglish());
        return Tools.correctText(text,tool);
    }
}
