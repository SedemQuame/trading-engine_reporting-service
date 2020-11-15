package com.trade.reportingservice.payments;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public @Data class Payment {
    @Id
    private String paymentId;
    private String userId;
    private String orderId;
    private int amount;
    private int statusId;

    public Payment(String userId, String orderId, int amount, int statusId) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
        this.statusId = statusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
