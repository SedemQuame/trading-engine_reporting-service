package com.trade.repository;

import com.trade.models.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {}
