package com.mayik.order_ms.infrastructure.adapters.in.rest.dto;

import com.mayik.order_ms.domain.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String userId;
    private List<String> items;

    public Order toDomain() {
        Order order = new Order();
        order.setUserId(this.userId);
        order.setItems(this.items);
        return order;
    }
}
