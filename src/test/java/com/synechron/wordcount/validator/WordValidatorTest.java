package com.synechron.wordcount.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"flower"})
    void shouldValidateToTrue(String word) {
        WordValidator validator = new WordValidator();
        assertTrue(validator.isValidWord(word));
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ",
            "flower2",
            "0flower"})
    void shouldValidateFalse(String word) {
        WordValidator validator = new WordValidator();
        assertFalse(validator.isValidWord(word));
    }

    @Test
    void shouldReturnFalseForNull() {
        WordValidator validator = new WordValidator();
        assertFalse(validator.isValidWord(null));
    }

}