package com.apress.prospring5.ch12;

import com.apress.prospring5.ch12.entities.Singer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RestClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientTest.class);

    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/list-data";

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindAll() throws JsonProcessingException {
        LOGGER.info("--> Testing retrieve all singers");
        String singersJson = restTemplate.getForObject(URL_GET_ALL_SINGERS, String.class);
        List<Singer> singers = objectMapper.readValue(singersJson, new TypeReference<List<Singer>>() {
        });
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers) {
        LOGGER.info(" ---- Listing singers:");
        singers.stream()
                .map(Singer::toString)
                .forEach(LOGGER::info);
    }
}
