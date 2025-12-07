package com.mayik.order_ms.infrastructure.adapters.out.saga;

import com.mayik.order_ms.application.port.out.SagaOrchestratorPort;
import com.mayik.order_ms.domain.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSagaOrchestratorAdapter implements SagaOrchestratorPort {

    private final RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE_NAME = "order-saga-exchange";

    public RabbitMQSagaOrchestratorAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void startSaga(Order order) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "order.created", order);
    }
}
