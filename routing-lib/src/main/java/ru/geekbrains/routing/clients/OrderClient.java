package ru.geekbrains.routing.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ms-order")
public interface OrderClient {
}
