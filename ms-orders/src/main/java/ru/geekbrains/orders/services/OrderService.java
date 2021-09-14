package ru.geekbrains.orders.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.geekbrains.orders.models.Cart;
import ru.geekbrains.orders.models.Order;
import ru.geekbrains.orders.repositories.OrderRepository;
import ru.geekbrains.routing.dtos.CartDto;
import ru.geekbrains.routing.dtos.OrderDto;
import ru.geekbrains.routing.dtos.ProductDto;
import ru.geekbrains.routing.feigns.ProductClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;

    private final ProductClient productClient;

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createFromUserCart(Long userId, UUID cartUuid, String address){
        CartDto cartDto = cartService.findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Order order = new Order(cart, userId, address);
        order = orderRepository.save(order);
        return toDto(order);
    }

    public OrderDto findOrderById(Long id){
        Order order = orderRepository.findById(id).get();
        List<Long> productIds = new ArrayList<>();
        order.getItems().forEach(orderItem ->  productIds.add(orderItem.getProductId()));
        List<ProductDto> products = productClient.findProductByIds(productIds);
        OrderDto orderDto = toDto(order);
        orderDto.setProducts(products);
        return orderDto;
    }

    public List<OrderDto> findAllOrdersByUserId(Long userId){
        return orderRepository.findAllByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private OrderDto toDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }

    private Order toEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, Order.class);
    }
}
