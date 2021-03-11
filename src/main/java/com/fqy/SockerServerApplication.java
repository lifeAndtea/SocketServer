package com.fqy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.fqy.qzdtest")
@Configuration
public class SockerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SockerServerApplication.class,args);
    }
}
