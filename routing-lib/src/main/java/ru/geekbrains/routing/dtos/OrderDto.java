package ru.geekbrains.routing.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Long userId;

    private List<ProductDto> products;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
