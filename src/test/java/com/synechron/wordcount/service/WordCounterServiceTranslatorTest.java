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

@ExtendWith(MockitoExtension.class)
class WordCounterServiceTranslatorTest {

    WordCounterService wordCounterService;
    @Mock
    Translator translator;

    @BeforeEach
    void setup() {
        wordCounterService = new WordCounterService(translator, new WordValidator());
    }

    @Test
    void shouldIncrementWordCountOfDifferentLanguage() throws InvalidWordException {

        Mockito.when(translator.translate("flower")).thenReturn("flower");
        Mockito.when(translator.translate("flor")).thenReturn("flower");
        Mockito.when(translator.translate("blume")).thenReturn("flower");

        wordCounterService.add("flower");
        wordCounterService.add("flor");
        wordCounterService.add("blume");

        assertEquals(3, wordCounterService.getWordCount("flower"));
    }

}