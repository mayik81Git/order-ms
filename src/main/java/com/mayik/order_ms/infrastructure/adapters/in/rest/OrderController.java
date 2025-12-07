package com.mayik.order_ms.infrastructure.adapters.in.rest;

import com.mayik.order_ms.application.port.in.CreateOrderPort;
import com.mayik.order_ms.domain.model.Order;
import com.mayik.order_ms.infrastructure.adapters.in.rest.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderPort createOrderPort;

    public OrderController(CreateOrderPort createOrderPort) {
        this.createOrderPort = createOrderPort;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Order newOrder = new Order();
        newOrder.setUserId(username);
        newOrder.setItems(orderDto.getItems());

        createOrderPort.createOrder(newOrder);

        return ResponseEntity.accepted().body("Pedido aceptado para su procesamiento.");
    }
}
