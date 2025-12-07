package com.mayik.order_ms.application.service;

import com.mayik.order_ms.application.port.in.CreateOrderPort;
import com.mayik.order_ms.application.port.out.SagaOrchestratorPort;
import com.mayik.order_ms.domain.model.Order;
import com.mayik.order_ms.domain.model.OrderStatus;
import com.mayik.order_ms.infrastructure.adapters.out.persistence.repository.OrderPersistenceAdapter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService implements CreateOrderPort {

    private final OrderPersistenceAdapter persistenceAdapter;
    private final SagaOrchestratorPort sagaOrchestratorPort;

    public OrderService(OrderPersistenceAdapter persistenceAdapter, SagaOrchestratorPort sagaOrchestratorPort) {
        this.persistenceAdapter = persistenceAdapter;
        this.sagaOrchestratorPort = sagaOrchestratorPort;
    }

    @Override
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        order.setSagaId(UUID.randomUUID().toString());
        Order savedOrder = persistenceAdapter.save(order);
        sagaOrchestratorPort.startSaga(savedOrder);
        return savedOrder;
    }
}
