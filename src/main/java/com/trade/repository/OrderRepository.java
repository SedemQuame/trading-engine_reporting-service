package com.trade.repository;

import com.trade.models.Order;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, String>, ReactiveQueryByExampleExecutor<Order> {
}
