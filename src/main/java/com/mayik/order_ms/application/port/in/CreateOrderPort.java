package com.mayik.order_ms.application.port.in;

import com.mayik.order_ms.domain.model.Order;

public interface CreateOrderPort {
    Order createOrder(Order order);
}
