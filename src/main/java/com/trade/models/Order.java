package com.trade.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public @Data class Order {
    @Id
    private @Getter @Setter String orderId;
    private @Getter @Setter String userId;
    private @Getter @Setter int unitPrice;
    private @Getter @Setter String tickerSymbol;
    private @Getter @Setter int statusId;
    private @Getter @Setter int quantity;
    private @Getter @Setter int transactionId;
    private @Getter @Setter String dateCreated;
    private @Getter @Setter String dateModified;
    private @Getter @Setter int orderTypeId;

    public Order(String userId, int unitPrice, String tickerSymbol, int statusId, int quantity, int transactionId, String dateCreated, String dateModified, int orderTypeId) {
        this.userId = userId;
        this.unitPrice = unitPrice;
        this.tickerSymbol = tickerSymbol;
        this.statusId = statusId;
        this.quantity = quantity;
        this.transactionId = transactionId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.orderTypeId = orderTypeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }
}

