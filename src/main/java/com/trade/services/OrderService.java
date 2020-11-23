package com.trade.services;

import com.trade.models.Order;
import com.trade.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public static void updateOrder() {

    }

    //    test orders route
    @GetMapping("/orders")
    public String root(){
        return "Order Routing Service";
    }

//    return all registered orders in the system
    @GetMapping("/orders/all")
    public Flux<Order> allOrders(){
        return repository.findAll();
    }

//    filter orders in database by id
    @GetMapping("/order/get/{orderId}")
    public Mono<Order> orderById(@PathVariable String orderId){
        return repository.findById(orderId);
    }

//    creating orders
    @PostMapping("/order/create")
    Mono<Void> createOrder(@RequestBody Order newOrder){
        Mono<Order> saveToDatabase = repository.save(newOrder);
        return Mono.when(saveToDatabase);
    }

//    deleting orders in database by id
    @DeleteMapping("/order/delete/{orderId}")
    public Mono<Void> deleteOrder(@PathVariable String orderId){
        return Mono.when(repository.deleteById(orderId));
    }

//    Updating orders in database by id
    @PutMapping("/order/update/{orderId}")
    public Mono<Order> updateOrder(@PathVariable String orderId, @RequestBody Order update){
        Mono<Order> updateDocument = repository.findById(orderId);
        updateDocument.map(order -> {
            order.setUserId(update.getUserId());
            order.setUnitPrice(update.getUnitPrice());
            order.setTickerSymbol(update.getTickerSymbol());
            order.setStatusId(update.getStatusId());
            order.setQuantity(update.getQuantity());
            order.setTransactionId(update.getTransactionId());
            order.setDateCreated(update.getDateCreated());
            order.setDateModified(update.getDateModified());
            return repository.save(order);
        });

        return updateDocument;
    }

}
