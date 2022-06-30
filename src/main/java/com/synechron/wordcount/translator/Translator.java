package com.synechron.wordcount.translator;

import org.springframework.stereotype.Component;

@Component
public class Translator {
    public String translate(String word) {
        return word;
    }
}
