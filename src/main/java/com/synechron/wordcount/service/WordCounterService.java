package com.synechron.wordcount.service;

import com.synechron.wordcount.exception.InvalidWordException;
import com.synechron.wordcount.translator.Translator;
import com.synechron.wordcount.validator.WordValidator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class WordCounterService {
    private final Map<String, Integer> wordCounter;
    private final WordValidator wordValidator;
    private final Translator translator;


    public WordCounterService(Translator translator,
                              WordValidator wordValidator) {
        this.wordValidator = wordValidator;
        this.translator = translator;
        this.wordCounter = new ConcurrentHashMap<>();
    }

    public void add(String word) throws InvalidWordException {
        if(!wordValidator.isValidWord(word)) {
            throw new InvalidWordException();
        }
        wordCounter.merge(translator.translate(word), 1, Integer::sum);
    }

    public int getWordCount(String word) {
        return wordCounter.getOrDefault(word, 0);
    }
}
