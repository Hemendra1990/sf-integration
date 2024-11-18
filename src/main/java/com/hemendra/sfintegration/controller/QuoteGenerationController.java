package com.hemendra.sfintegration.controller;

import com.hemendra.sfintegration.service.QuoteGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{sObject}/{recordId}")
    public ResponseEntity<Map> generateQuote(@PathVariable("sObject") String sObject, @PathVariable("recordId") String recordId) {
        Map record = quoteGenerationService.generateQuote(sObject, recordId);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }
}
