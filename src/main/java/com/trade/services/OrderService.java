package com.trade.services;

import com.trade.models.Order;
import com.trade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public static void updateOrder() {

    }

    //    test orders route
    @GetMapping("/orders")
    @CrossOrigin
    public String root() {
        return "Order Routing Service";
    }

    //    return all registered orders in the system
    @GetMapping("/orders/all")
    @CrossOrigin
    public Flux<Order> allOrders() {
        return repository.findAll();
    }

    //    filter orders in database by id
    @GetMapping("/order/get/{orderId}")
    @CrossOrigin
    public Mono<Order> orderById(@PathVariable String orderId) {
        return repository.findById(orderId);
    }

    //    creating orders
    @PostMapping("/order/create")
    @CrossOrigin
    Mono<Void> createOrder(@RequestBody Order newOrder) {
        Mono<Order> saveToDatabase = repository.save(newOrder);
        return Mono.when(saveToDatabase);
    }

    //    deleting orders in database by id
    @DeleteMapping("/order/delete/{orderId}")
    @CrossOrigin
    public Mono<Void> deleteOrder(@PathVariable String orderId) {
        return Mono.when(repository.deleteById(orderId));
    }

    //    Updating orders in database by id
    @PutMapping("/order/update/{orderId}")
    @Transactional
    @CrossOrigin
    Mono<Order> updateOrder(@PathVariable String orderId, @RequestBody Order update) {
        return this.repository.findById(orderId)
                .flatMap(order -> {
                    order.setUserId(update.getUserId());
                    order.setPrice(update.getPrice());
                    order.setTicker(update.getTicker());
                    order.setStatus(update.getStatus());
                    order.setQuantity(update.getQuantity());
                    order.setDateCreated(update.getDateCreated());
                    order.setDateModified(update.getDateModified());
                    return this.repository.save(order);
                });
    }
}
