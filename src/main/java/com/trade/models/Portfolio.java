package com.trade.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public @Data class Portfolio {
    @Id
    private String portfolioId;
    private String userId;
    private String statusId;

    public Portfolio(String userId, String statusId) {
        this.userId = userId;
        this.statusId = statusId;
    }
}
