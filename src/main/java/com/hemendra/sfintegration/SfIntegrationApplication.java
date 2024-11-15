package com.hemendra.sfintegration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class SfIntegrationApplication {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(SfIntegrationApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String clientId = env.getRequiredProperty("spring.security.oauth2.client.registration.salesforce.client-id");
        String clientSecret = env.getProperty("spring.security.oauth2.client.registration.salesforce.client-secret");
        String username = env.getProperty("spring.security.oauth2.client.registration.salesforce.username");
        String password = env.getProperty("spring.security.oauth2.client.registration.salesforce.password");
        String forceDomain = env.getProperty("SF_DOMAIN");

        log.info("Client ID: {}, Client Secret: {}", clientId, clientSecret);
        log.info("Username: {}, Password: {}", username, password);
        log.info("Force Domain: {}", forceDomain);
    }

}
