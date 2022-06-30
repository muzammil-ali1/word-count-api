package com.synechron.wordcount.controller;

import com.synechron.wordcount.exception.InvalidWordException;
import com.synechron.wordcount.service.WordCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordCount")
public class WordCountController {

    private final WordCounterService wordCounterService;

    public WordCountController(WordCounterService wordCounterService) {
        this.wordCounterService = wordCounterService;
    }

    @PostMapping(value = "/{word}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String word) throws InvalidWordException {
        wordCounterService.add(word);
    }

    @GetMapping(value = "/{word}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getWordCount(@PathVariable String word) {
        return wordCounterService.getWordCount(word);
    }
}
