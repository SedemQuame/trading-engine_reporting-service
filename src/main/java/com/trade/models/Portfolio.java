package com.trade.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public @Data class Portfolio {
    @Id
    private String portfolioId;
    private String userId;
    private String statusId;
    private List<String> orderList;

    public Portfolio(String userId, String statusId) {
        this.userId = userId;
        this.statusId = statusId;
        this.orderList = new ArrayList<>();
    }
}
