package com.trade.stocks;

import com.trade.models.Stock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends ReactiveCrudRepository<Stock, String> {}
