package com.example.news.controller;

import com.example.news.service.ProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customnews")
public class ProxyController {

    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping
    public ResponseEntity<String[]> provideCustomNews() {
        return new ResponseEntity<>(proxyService.provideCustomNews(), HttpStatus.OK);
    }
}
