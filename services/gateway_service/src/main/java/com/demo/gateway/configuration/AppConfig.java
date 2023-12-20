package com.demo.gateway.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("BookingChannel")
    public ManagedChannel bookingChannel(GrpcConfiguration grpcConfiguration) {
        String host = grpcConfiguration.getBooking().getHost();
        int port = grpcConfiguration.getBooking().getPort();

        log.info("Init booking grpc channel at: {}:{}", host, port);
        return buildManagedChannel(host, port);
    }

    @Bean("AuthChannel")
    public ManagedChannel authChannel(GrpcConfiguration grpcConfiguration) {
        String host = grpcConfiguration.getAuth().getHost();
        int port = grpcConfiguration.getAuth().getPort();

        log.info("Init auth grpc channel at: {}:{}", host, port);
        return buildManagedChannel(host, port);
    }

    private ManagedChannel buildManagedChannel(String host, int port) {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }
}
