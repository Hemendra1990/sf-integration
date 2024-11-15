package com.hemendra.sfintegration.controller;

import com.hemendra.sfintegration.service.QuoteGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate-quote")
public class QuoteGenerationController {

    private final QuoteGenerationService quoteGenerationService;

    @GetMapping
    public ResponseEntity<Map> generateQuote(@RequestParam("id") String id) {
        Map record = quoteGenerationService.generateQuote(id);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }
}
