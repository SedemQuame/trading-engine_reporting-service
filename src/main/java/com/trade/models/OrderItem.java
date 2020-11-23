package com.trade.models;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = 2228145111069333864L;

    private String product;
    private int  quantity;
    private double price;
    private String side;

    public OrderItem(String product, int quantity, double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                '}';
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSide() {
        return side;
    }
}
