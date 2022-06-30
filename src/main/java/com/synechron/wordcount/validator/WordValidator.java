package com.synechron.wordcount.validator;

import org.springframework.stereotype.Component;

@Component
public class WordValidator {
    public boolean isValidWord(String word){
        return word != null && word.matches("[a-zA-Z]+");
    }
}
