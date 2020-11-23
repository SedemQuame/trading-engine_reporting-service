package com.trade;

import com.trade.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class ReportingServiceApplication {
	private static Jedis jedis = null;
	private final String ORDER_TO_REPORTING_SERVICE_CHANNEL = "C2";

	@Autowired
	private static OrderRepository repository = null;

	public ReportingServiceApplication(OrderRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
//		Instance for running the app
		SpringApplication.run(ReportingServiceApplication.class, args);

// TODO: 11/23/20 Connecting to the redis server to get order updates.
		try {
//			created jedis connection.
			jedis = getConnection();

//			get list of validated orders from a channel.
//            creating JedisPubSub object for subscribing to channels
			JedisPubSub jedisPubSub = new JedisPubSub() {
				@Override
				public void onMessage(String channel, String message) {
					System.out.println("Printing published message.");
					System.out.println(message);
					List validatedOrders = (List) ObjectSerializer.unserizlize(Base64.getDecoder().decode(message.getBytes()));

					assert validatedOrders != null;
					validatedOrders.forEach(validatedOrder -> {
//						System.out.println(validatedOrder.toString());
//						Mono<Order> updateDocument = repository.findById(validatedOrder.get);
//						updateDocument.map(order -> {
//							order.setUserId(update.getUserId());
//							order.setUnitPrice(update.getUnitPrice());
//							order.setTickerSymbol(update.getTickerSymbol());
//							order.setStatusId(update.getStatusId());
//							order.setQuantity(update.getQuantity());
//							order.setTransactionId(update.getTransactionId());
//							order.setDateCreated(update.getDateCreated());
//							order.setDateModified(update.getDateModified());
//							return repository.save(order);
//						});
					});


				}

				@Override
				public void onPMessage(String pattern, String channel, String message) {
					super.onPMessage(pattern, channel, message);
				}
			};
			while (true) {
				jedis.subscribe(jedisPubSub, "C1");
			}


		} catch (URISyntaxException e ) {
			e.printStackTrace();
		}

	}

	private static Jedis getConnection() throws URISyntaxException {
		URI redisURI = null;
		if(System.getenv("REDIS_URL") != null){
			redisURI = new URI(System.getenv("REDIS_URL"));
		}else{
			redisURI = new URI("localhost:8090");
		}
		return (new Jedis(redisURI));
	}
}
