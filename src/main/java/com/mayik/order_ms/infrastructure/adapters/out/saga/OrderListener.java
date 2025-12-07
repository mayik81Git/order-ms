package com.mayik.order_ms.infrastructure.adapters.out.saga;

import com.mayik.order_ms.domain.model.Order;
import com.mayik.order_ms.domain.model.OrderStatus;
import com.mayik.order_ms.infrastructure.adapters.out.persistence.repository.OrderPersistenceAdapter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final OrderPersistenceAdapter persistenceAdapter;

    public OrderListener(OrderPersistenceAdapter persistenceAdapter) {
        this.persistenceAdapter = persistenceAdapter;
    }

    @RabbitListener(queues = "order-cancel-queue")
    public void handleOrderCancel(@Payload Order order) {
        System.out.println("Order-ms: Recibido comando de compensación para cancelar pedido " + order.getId());
        order.setStatus(OrderStatus.CANCELLED);
        persistenceAdapter.save(order);
    }
}
