package com.trade.services;

import com.trade.models.Payment;
import com.trade.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PaymentService {
    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/payment")
    @CrossOrigin
    public String root(){
        return "Payment routing service";
    }

    @GetMapping("/payments/all")
    @CrossOrigin
    public Flux<Payment> allPayments(){
        return repository.findAll();
    }

    @GetMapping("/payments/all/{userId}")
    @CrossOrigin
    public Flux<Payment> allPaymentsByUserId(@PathVariable String userId){
//        change the internal structure of this function
//        to return all documents, with similar user ids.
        return repository.findAll();
    }

    @PostMapping("/payment/create")
    @CrossOrigin
    Mono<Void> createPayment(@RequestBody Payment newPayment){
        Mono<Payment> saveToDatabase = repository.save(newPayment);
        return Mono.when(saveToDatabase);
    }

//    deleting payment in database by id
    @DeleteMapping("/payment/delete/{paymentId}")
    @CrossOrigin
    public Mono<Void> deletePaymentRecord(@PathVariable String paymentId){
        return Mono.when(repository.deleteById(paymentId));
    }

}
