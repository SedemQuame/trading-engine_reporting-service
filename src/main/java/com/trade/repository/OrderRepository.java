package com.trade.repository;

import com.trade.models.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
    Flux<Order> findAllByUserIdEquals(String userId);
}
