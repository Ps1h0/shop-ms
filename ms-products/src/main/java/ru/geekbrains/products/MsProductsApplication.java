package ru.geekbrains.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "ru.geekbrains")
public class MsProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductsApplication.class, args);
    }

}
