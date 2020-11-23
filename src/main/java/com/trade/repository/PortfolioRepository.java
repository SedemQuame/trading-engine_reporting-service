package com.trade.repository;

import com.trade.models.Portfolio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends ReactiveCrudRepository<Portfolio, String> {
}
