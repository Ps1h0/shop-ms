package ru.geekbrains.routing.dtos;

import lombok.Data;

@Data
public class OrderItemDto {

    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;
}
