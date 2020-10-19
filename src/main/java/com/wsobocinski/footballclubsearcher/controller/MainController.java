package com.wsobocinski.footballclubsearcher.controller;

import com.wsobocinski.footballclubsearcher.model.Page;
import com.wsobocinski.footballclubsearcher.model.PagesModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Controller
public class MainController {

    private static final String API_URL = "https://en.wikipedia.org/w/rest.php/v1/search/page?limit=10&q=";
    private static final String WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
    private RestTemplate restTemplate;

    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{query}")
    public ResponseEntity<String> getFootballClubUrl(@PathVariable String query) {
        PagesModel apiResult = restTemplate.getForObject(API_URL + query, PagesModel.class);
        Optional<Page> filteredResult =  apiResult.getPages().stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getDescription().contains("football club"))
                .findAny();

        return filteredResult.map(page -> new ResponseEntity<>(WIKIPEDIA_URL + page.getKey(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("No results found", HttpStatus.NOT_FOUND));
    }
}
