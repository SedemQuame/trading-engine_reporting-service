package com.trade.reportingservice.portfolios;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class PortfolioService {
    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/portfolio")
    public String root(){
        return "Portfolio routing service";
    }

    @GetMapping("/portfolios/all")
    public Flux<Portfolio> allPortfolios(){
        return repository.findAll();
    }

    @PostMapping("/portfolio/create")
    Mono<Void> createPortfolio(@RequestBody Portfolio newPortfolio){
        Mono<Portfolio> saveToDatabase = repository.save(newPortfolio);
        return Mono.when(saveToDatabase);
    }

//    deleting portfolio in database by id
    @DeleteMapping("/portfolio/delete/{portfolioId}")
    public Mono<Void> deletePortfolioRecord(@PathVariable String portfolioId){
        return Mono.when(repository.deleteById(portfolioId));
    }

//    add an update to utility for portfolios
}
