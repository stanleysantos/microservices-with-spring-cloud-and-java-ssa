package br.com.erudio.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.Sender;
import zipkin2.reporter.urlconnection.URLConnectionSender;

@Configuration
public class ZipkinCustomSenderConfig {

    private final Logger logger = LoggerFactory.getLogger(ZipkinCustomSenderConfig.class);

    @Value("${spring.zipkin.base-url:http://localhost:9411}")
    private String zipkinBaseUrl;

    @Bean
    public Sender zipkinSender() {
        logger.debug("zipkinBaseUrl: [" + zipkinBaseUrl + "]");
        String endpoint = zipkinBaseUrl + "/api/v2/spans";
        return URLConnectionSender.newBuilder()
                .endpoint(endpoint)
                .build();
    }

}