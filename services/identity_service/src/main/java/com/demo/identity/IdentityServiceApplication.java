package com.demo.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.demo.crud", "com.demo.identity"})
@EnableJpaRepositories("com.demo.crud.identity.repositories")
@EntityScan("com.demo.crud.identity.models")
public class IdentityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceApplication.class, args);
    }

}
