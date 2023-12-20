package com.demo.gateway.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "grpc")
@Setter
@Getter
public class GrpcConfiguration {
    private GrpcConfig booking;
    private GrpcConfig auth;
    @Getter
    @Setter
    public static class GrpcConfig {
        private String host;
        private int port;
    }
}
