package com.trade.reportingservice.portfolios;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends ReactiveCrudRepository<Portfolio, String> {
}
