package com.trade.services;

import com.trade.models.CreationResponse;
import com.trade.models.Portfolio;
import com.trade.repository.PortfolioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PortfolioService {
    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/portfolio")
    @CrossOrigin
    public String root(){
        return "Portfolio routing service";
    }

    @GetMapping("/portfolios/all")
    @CrossOrigin
    public Flux<Portfolio> allPortfolios(){
        return repository.findAll();
    }

    @PostMapping("/portfolio/create")
    @CrossOrigin
    Mono<CreationResponse> createPortfolio(@RequestBody Portfolio newPortfolio){
        Mono<Portfolio> saveToDatabase = repository.save(newPortfolio);
        Mono.when(saveToDatabase);
        return Mono.just((new CreationResponse("success", "Created portfolio success")));
    }
//    Mono<Void> createPortfolio(@RequestBody Portfolio newPortfolio){
//        Mono<Portfolio> saveToDatabase = repository.save(newPortfolio);
//        return Mono.when(saveToDatabase);
//    }

//    deleting portfolio in database by id
    @DeleteMapping("/portfolio/delete/{portfolioId}")
    @CrossOrigin
    public Mono<Void> deletePortfolioRecord(@PathVariable String portfolioId){
        return Mono.when(repository.deleteById(portfolioId));
    }
}
