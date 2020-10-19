package com.wsobocinski.footballclubsearcher.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    MainController mainController = new MainController(restTemplate);

    @Test
    void getLiverpoolUrlShouldReturnLiverpoolFcUrl() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("liverpool");
        assertEquals("https://en.wikipedia.org/wiki/Liverpool_F.C.", result.getBody());
    }

    @Test
    void getLiverpoolUrlShouldNotReturnLiverpoolCityUrl() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("liverpool");
        assertNotEquals("https://en.wikipedia.org/wiki/Liverpool", result.getBody());
    }

    @Test
    void getLiverpoolStatusShouldReturnStatusOk() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("liverpool");
        assertEquals(HttpStatus.OK,
                result.getStatusCode());
    }

    @Test
    void getBarcelonaUrlShouldReturnFcBarcelonaUrl() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("barcelona");
        assertEquals("https://en.wikipedia.org/wiki/FC_Barcelona", result.getBody());
    }

    @Test
    void getBarcelonaStatusShouldReturnStatusOk() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("barcelona");
        assertEquals(HttpStatus.OK,
                result.getStatusCode());
    }

    @Test
    void getRandomNumbersStatusShouldReturnStatusNotFound() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("8372994023");
        assertEquals(HttpStatus.NOT_FOUND,
                result.getStatusCode());
    }

    @Test
    void getRandomNumbersUrlShouldReturnNoResultsFound() {
        ResponseEntity<String> result = mainController.getFootballClubUrl("8372994023");
        assertEquals("No results found", result.getBody());
    }
}