package com.erpexample;

import erp.ERP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApp {
    public static void main(String[] args) {
        ERP.useAnnotation();
        SpringApplication.run(ExampleApp.class, args);
    }
}
