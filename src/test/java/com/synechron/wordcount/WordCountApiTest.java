package com.synechron.wordcount;


import com.synechron.wordcount.service.WordCounterService;
import com.synechron.wordcount.translator.Translator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class WordCountApiTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WordCounterService wordCounterService;

    @MockBean
    private Translator translator;

    private String basePath;

    @BeforeEach
    void setup(){
        basePath = "http://localhost:" + port + "/wordCount/";
    }

    @Test
    public void shouldBeAbleToGetCountOfAWord() {
        mockTranslator("green", "green");
        postToWordCount("green");
        postToWordCount("green");

        assertThat(this.restTemplate.getForObject(basePath + "green",
                Integer.class)).isEqualTo(2);
    }

    @Test
    public void shouldBeAbleToGetCountOfAWordFromDifferentLanguages() {
        mockTranslator("flor", "flower");
        mockTranslator("blume", "flower");
        mockTranslator("flower", "flower");

        postToWordCount("flower");
        postToWordCount("flor");
        postToWordCount("blume");

        assertThat(this.restTemplate.getForObject(basePath + "flower",
                Integer.class)).isEqualTo(3);
    }

    @Test
    public void shouldBeAbleToAddAWord() {
        mockTranslator("blue", "blue");
        postToWordCount("blue");

        assertEquals(1, wordCounterService.getWordCount("blue"));
    }

    @Test
    public void shouldBeAbleToAddAWordMultipleTimes() {
        mockTranslator("rose", "rose");
        postToWordCount("rose");
        postToWordCount("rose");

        assertEquals(2, wordCounterService.getWordCount("rose"));
    }

    @Test
    public void applicationContextTest() {
        WordCountApplication.main(new String[] {});
    }

    private void postToWordCount(String word) {
        ResponseEntity<String> response = this.restTemplate.postForEntity(URI.create(basePath + word), "", String.class);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    private void mockTranslator(String originalWord, String translation) {
        Mockito.when(translator.translate(originalWord)).thenReturn(translation);
    }
}