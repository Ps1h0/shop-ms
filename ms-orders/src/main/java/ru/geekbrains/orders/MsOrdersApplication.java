package ru.geekbrains.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "ru.geekbrains")
@SpringBootApplication(scanBasePackages = "ru.geekbrains")
public class MsOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrdersApplication.class, args);
    }

}
