package com.hy.rabbitmq_springboot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", declare = "false", autoDelete = "true"))
public class HelloCustomer {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("message = " + message);
    }

}
