package com.mayik.order_ms.application.port.out;

import com.mayik.order_ms.domain.model.Order;

public interface SagaOrchestratorPort {
    void startSaga(Order order);
}