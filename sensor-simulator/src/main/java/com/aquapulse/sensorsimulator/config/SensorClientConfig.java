package com.aquapulse.sensorsimulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class SensorClientConfig {
    @Bean
    public RestClient restClient(){
        return RestClient.builder().
                baseUrl("http://localhost:1291").
                build();
    }
}
