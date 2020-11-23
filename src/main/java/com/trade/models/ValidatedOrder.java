package com.trade.models;

import java.io.Serializable;

public class ValidatedOrder implements Serializable {
    private static final long serialVersionUID = 2228145111069333864L;
    private String id;
    private OrderItem orderItem;
    private String status;
    private String userId;

    public ValidatedOrder() {

    }

    public ValidatedOrder(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ValidatedOrder(OrderItem orderItem, String status, String userId) {
        this.orderItem = orderItem;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ValidatedOrder{" +
                "orderItem=" + orderItem.toString() +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }
}


