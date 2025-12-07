package com.mayik.order_ms.infrastructure.adapters.out.persistence.repository;

import com.mayik.order_ms.domain.model.Order;
import com.mayik.order_ms.infrastructure.adapters.out.persistence.repository.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceAdapter {

    private final OrderRepository orderRepository;

    public OrderPersistenceAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setUserId(order.getUserId());
        entity.setItems(order.getItems());
        entity.setStatus(order.getStatus());
        OrderEntity savedEntity = orderRepository.save(entity);

        Order savedOrder = new Order();
        savedOrder.setId(savedEntity.getId());
        savedOrder.setUserId(savedEntity.getUserId());
        savedOrder.setItems(savedEntity.getItems());
        savedOrder.setStatus(savedEntity.getStatus());
        return savedOrder;
    }
}
