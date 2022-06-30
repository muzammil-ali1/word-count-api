package com.synechron.wordcount.service;

import com.synechron.wordcount.exception.InvalidWordException;
import com.synechron.wordcount.translator.Translator;
import com.synechron.wordcount.validator.WordValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WordCounterThreadSafetyTest {

    @Mock
    Translator translator;

    @Test
    public void testCounterWithConcurrency() throws InterruptedException {
        Mockito.when(translator.translate("flower")).thenReturn("flower");
        Mockito.when(translator.translate("plant")).thenReturn("plant");

        int numberOfThreads = 100;
        ExecutorService service = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        WordCounterService wordCounterService = new WordCounterService(translator, new WordValidator());
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                try {
                    wordCounterService.add("flower");
                    wordCounterService.add("plant");
                } catch (InvalidWordException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
            });
        }
        latch.await();
        assertEquals(numberOfThreads, wordCounterService.getWordCount("flower"));
        assertEquals(numberOfThreads, wordCounterService.getWordCount("plant"));
    }

}
