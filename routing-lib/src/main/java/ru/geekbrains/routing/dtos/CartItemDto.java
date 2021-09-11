package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CartItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;
}
