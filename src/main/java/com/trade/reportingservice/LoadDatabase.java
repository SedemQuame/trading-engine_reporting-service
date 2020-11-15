package com.trade.reportingservice;

import com.trade.reportingservice.orders.Order;
import com.trade.reportingservice.orders.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner init(OrderRepository repository){
        return args -> Flux.just(
            new Order("ASDJL12", 1, "AAPL", 1, 120, 201, "15-11-2019", "15-11-2020", 2),
            new Order("JSLFJ45", 10, "AMZN", 12, 1_000, 201, "07-08-2009", "23-12-2010", 3),
            new Order("IEWUE23", 100, "TSL", 15, 1_320, 401, "09-01-2015", "16-11-2019", 4)
        ).flatMap(repository::save)
        .subscribe(System.out::println);
    }
}
