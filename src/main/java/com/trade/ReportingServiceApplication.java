package com.trade;

import com.trade.models.Order;
import com.trade.models.OrderItem;
import com.trade.models.ValidatedOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ReportingServiceApplication {
    private static final int PORT = 8090;
    private static Jedis jedis = null;
    private final String ORDER_TO_REPORTING_SERVICE_CHANNEL = "C2";

//	@Autowired
//	private static OrderRepository repository = null;
//
//	public ReportingServiceApplication(OrderRepository repository) {
//		this.repository = repository;
//	}

    public static void main(String[] args) {
//		Instance for running the app
        SpringApplication.run(ReportingServiceApplication.class, args);

// TODO: 11/23/20 Connecting to the redis server to get order updates. ==> DONE
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
                    List<ValidatedOrder> validatedOrders = (List) ObjectSerializer.unserizlize(Base64.getDecoder().decode(message.getBytes()));


                    assert validatedOrders != null;
                    validatedOrders.forEach(validatedOrder -> {
                        String orderId = validatedOrder.getId();
//                        String orderId = (new Scanner(System.in)).next();

                        // TODO: 11/23/20 pass validated items from the order validation service here.
//                        OrderItem update = validatedOrder.getOrderItem();
                        Order update = new Order("JSLFJ46", 10, "AAPL", 12, 1_000, 201, "07-08-2009", "23-12-2010", 3);

                        RestTemplate restTemplate = new RestTemplate();
                        String endPoint = "http://localhost:8080/order/update/" + orderId;
//                        System.out.println(endPoint);

                        restTemplate.put(endPoint, update, Order.class);
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


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private static Jedis getConnection() throws URISyntaxException {
        URI redisURI = null;
        if (System.getenv("REDIS_URL") != null) {
            redisURI = new URI(System.getenv("REDIS_URL"));
        } else {
            redisURI = new URI("http://localhost:" + PORT);
        }
        return (new Jedis(redisURI));
    }
}
