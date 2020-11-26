package com.trade;

import com.trade.models.Order;
import com.trade.utility.ObjectSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

@SpringBootApplication
public class ReportingService {
    private static final int PORT = 8090;
    private final static String ORDER_TO_REPORTING_SERVICE_CHANNEL = "C2";
    private static final String LOCAL_URL = "http://localhost:8080/";
    private static final String HEROKU_URL = "https://trade-order-validator.herokuapp.com/";

    public static void main(String[] args) {
//		Instance for running the app
        SpringApplication.run(ReportingService.class, args);

// TODO: 11/23/20 Connecting to the redis server to get order updates. ==> DONE
        try {
//			created jedis connection.
            Jedis jedis = getConnection();

//			get list of validated orders from a channel.
//          creating JedisPubSub object for subscribing to channels
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    List<Order> validatedOrders = (List) ObjectSerializer.unserizlize(Base64.getDecoder().decode(message.getBytes()));

                    assert validatedOrders != null;
                    validatedOrders.forEach(updatedOrder -> {
                        String orderId = updatedOrder.getOrderId();
                        // TODO: 11/23/20 pass validated items from the order validation service here.
                        RestTemplate restTemplate = new RestTemplate();
                        String endPoint = "";
                        if (System.getenv("REDIS_URL") != null) {
                            endPoint += HEROKU_URL;
                        }else {
                            endPoint += LOCAL_URL;
                        }
                        endPoint += "order/update/" + orderId;
                        restTemplate.put(endPoint, updatedOrder, Order.class);
                    });
                }

                @Override
                public void onPMessage(String pattern, String channel, String message) {
                    super.onPMessage(pattern, channel, message);
                }
            };
            while (true)
                jedis.subscribe(jedisPubSub, ORDER_TO_REPORTING_SERVICE_CHANNEL);
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
