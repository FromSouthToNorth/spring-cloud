package com.hy.rabbitmq_springboot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {

    // 消费者-1
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveOne(String message) {
        System.out.println("messageOne = " + message);
    }

    // 消费者-2
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receiveTwo(String message) {
        System.out.println("messageTwo = " + message);
    }

}
