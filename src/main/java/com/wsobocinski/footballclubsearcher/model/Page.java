package com.wsobocinski.footballclubsearcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    private Integer id;
    private String key;
    private String description;
}
