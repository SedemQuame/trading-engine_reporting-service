package com.trade;

import com.trade.models.Order;
import com.trade.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner init(OrderRepository repository){
        return args -> Flux.just(
            new Order("","ASDJL12", "AAPL", "unvalidated","15-11-2019", "15-11-2020", "BUY",1, 120),
            new Order("","JSLFJ45", "AMZN", "unvalidated", "07-08-2009", "23-12-2010", "SELL" ,12, 1_000),
            new Order("","IEWUE23", "TSL", "unvalidated", "09-01-2015", "16-11-2019", "SELL",15, 1_320)
        ).flatMap(repository::save)
        .subscribe(System.out::println);
    }
}
