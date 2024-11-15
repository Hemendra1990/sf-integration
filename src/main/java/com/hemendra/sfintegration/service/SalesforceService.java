package com.hemendra.sfintegration.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
@Slf4j
public class SalesforceService {

    @Autowired
    private WebClient salesforceWebClient;

    @Value("${sfq.salesforce.resource-path}")
    private String resourcePath;

    public Map getRecord(String recordId) {
        Mono<Map> response = salesforceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(resourcePath).build("Account", recordId))
                .retrieve().onStatus(HttpStatusCode::isError, errorResponse -> {
                    logErrorBody(errorResponse);
                    return Mono.error(new RuntimeException(String.format("Salesforce request on error status=%s, headers=%s",
                            errorResponse.statusCode(), errorResponse.headers().asHttpHeaders())));
                }).bodyToMono(Map.class);
        return response.block();
    }

    public String upsertResource(String resourceId, String jsonRequest) {
        Mono<String> response = salesforceWebClient
                .patch()
                .uri(uriBuilder -> uriBuilder
                        .path(resourcePath)
                        .build(resourceId))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonRequest)
                .retrieve()
                .onStatus(HttpStatusCode::isError, errorResponse -> {
                    logErrorBody(errorResponse);
                    return Mono.error(new RuntimeException(String.format("Salesforce request on error status=%s, headers=%s",
                            errorResponse.statusCode(), errorResponse.headers().asHttpHeaders())));
                })
                .bodyToMono(String.class);

        return response.block();
    }

    public static void logErrorBody(ClientResponse response) {
        if (log.isErrorEnabled()) {
            response.bodyToMono(String.class)
                    .publishOn(Schedulers.boundedElastic())
                    .subscribe(body -> log.error("Body of the #Salesforce error response: {}", body));
        }
    }

}