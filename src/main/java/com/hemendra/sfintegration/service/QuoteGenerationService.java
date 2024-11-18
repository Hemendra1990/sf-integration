package com.hemendra.sfintegration.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteGenerationService {
    private final SalesforceService salesforceService;

    public Map generateQuote(String recordId) {
        Map record = salesforceService.getRecord(recordId);
        log.info("{}", record);
        return record;
    }

    public Map generateQuote(String sObject, String recordId) {
        Map record = salesforceService.getRecord(sObject, recordId);
        return record;
    }
}
