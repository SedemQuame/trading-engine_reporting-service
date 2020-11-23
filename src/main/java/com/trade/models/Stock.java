package com.trade.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public @Data class Stock {
    @Id
    private String stockId;
    private String tickerSymbol;
    private String portfolioId;
    private int stockQuantity;

    public Stock(String tickerSymbol, String portfolioId, int stockQuantity) {
        this.tickerSymbol = tickerSymbol;
        this.portfolioId = portfolioId;
        this.stockQuantity = stockQuantity;
    }
}
