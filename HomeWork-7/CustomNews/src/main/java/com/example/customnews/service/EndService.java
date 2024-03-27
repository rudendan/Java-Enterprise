package com.example.customnews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EndService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${news.url}")
    private String url;

    public void callMethod() {
        ResponseEntity<String[]> response = restTemplate.getForEntity(url, String[].class);
        String[] articles = response.getBody();
        for (String article : articles) {
            System.out.println(article);
        }
    }
}
