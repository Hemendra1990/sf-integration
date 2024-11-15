package com.hemendra.sfintegration.controller;

import com.hemendra.sfintegration.service.QuoteGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate-quote")
public class QuoteGenerationController {

    private final QuoteGenerationService quoteGenerationService;

    @GetMapping
    public String generateQuote(@RequestParam("id") String id) {
        quoteGenerationService.generateQuote(id);
        return "Quote generated";
    }
}
