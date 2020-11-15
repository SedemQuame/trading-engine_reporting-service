package com.trade.reportingservice.orders;

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
}
