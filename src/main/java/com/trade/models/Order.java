package com.trade.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public @Data class Order implements Serializable {
    private static final long serialVersionUID = 2228145111069333864L;
    @Id
    private @Getter @Setter String orderId;
    private @Getter @Setter String userId;
    private @Getter @Setter String ticker;
    private @Getter @Setter String status;
    private @Getter @Setter String dateCreated;
    private @Getter @Setter String dateModified;
    private @Getter @Setter String side;
    private @Getter @Setter double price;
    private @Getter @Setter int quantity;

    public Order(String orderId, String userId, String ticker, String status, String dateCreated, String dateModified, String side, double price, int quantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.ticker = ticker;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

