package com.mayik.order_ms.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Order implements Serializable {
    private Long id;
    private String userId;
    private List<String> items;
    private OrderStatus status;
    private String sagaId;

}
