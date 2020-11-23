package com.trade.stocks;

import com.trade.models.Stock;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StockService {
    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }
    
    //    test stocks route
    @GetMapping("/stocks")
    public String root(){
        return "Stock Routing Service";
    }

    //    return all registered stocks in the system
    @GetMapping("/stocks/all")
    public Flux<Stock> allStocks(){
        return repository.findAll();
    }

    //    filter stocks in database by id
    @GetMapping("/stocks/get/{stockId}")
    public Mono<Stock> stocksById(@PathVariable String stockId){
        return repository.findById(stockId);
    }

    //    creating stocks
    @PostMapping("/stocks/create")
    Mono<Void> createStock(@RequestBody Stock newStock){
        Mono<Stock> saveToDatabase = repository.save(newStock);
        return Mono.when(saveToDatabase);
    }

    //    deleting stocks in database by id
    @DeleteMapping("/stocks/delete/{stockId}")
    public Mono<Void> deleteStock(@PathVariable String stockId){
        return Mono.when(repository.deleteById(stockId));
    }
}
