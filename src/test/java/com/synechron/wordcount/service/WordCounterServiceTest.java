package com.synechron.wordcount.service;

import com.synechron.wordcount.exception.InvalidWordException;
import com.synechron.wordcount.translator.Translator;
import com.synechron.wordcount.validator.WordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WordCounterServiceTest {
    WordCounterService wordCounterService;
    @Mock
    Translator translator;

    @BeforeEach
    void setup() {
        wordCounterService = new WordCounterService(translator, new WordValidator());
    }

    @Test
    void shouldAddANewWord() throws InvalidWordException {
        mockTranslator("flower");
        wordCounterService.add("flower");

        assertEquals(1, wordCounterService.getWordCount("flower"));
    }

    @Test
    void shouldBeAbleToAddAWordMoreThanOnce() throws InvalidWordException {
        mockTranslator("flower");
        wordCounterService.add("flower");
        wordCounterService.add("flower");

        assertEquals(2, wordCounterService.getWordCount("flower"));
    }

    @Test
    void shouldBeAbleToAddMultipleWords() throws InvalidWordException {
        mockTranslator("flower");
        mockTranslator("branch");

        wordCounterService.add("flower");
        wordCounterService.add("branch");
        wordCounterService.add("flower");
        wordCounterService.add("branch");
        wordCounterService.add("branch");

        assertEquals(2, wordCounterService.getWordCount("flower"));
        assertEquals(3, wordCounterService.getWordCount("branch"));
    }

    @Test
    void shouldThrowErrorWhenAddNonAlphabeticWord(){
        assertThrows(InvalidWordException.class, () -> wordCounterService.add("flower32"));
        assertEquals(0, wordCounterService.getWordCount("flower32"));
    }

    @Test
    void shouldThrowErrorWhenAddNull(){
        assertThrows(InvalidWordException.class, () -> wordCounterService.add(null));
    }

    private void mockTranslator(String str) {
        Mockito.when(translator.translate(str)).thenReturn(str);
    }



}