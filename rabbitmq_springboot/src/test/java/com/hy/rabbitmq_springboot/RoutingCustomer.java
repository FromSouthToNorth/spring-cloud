package com.hy.rabbitmq_springboot;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RoutingCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 指定交换机名称及类型
                    key = {"info", "error", "warn"}
            )
    })
    public void receiveOne(String message) {
        System.out.println("messageOne = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "directs", type = "direct"), // 指定交换机名称及类型
                    key = {"error"}
            )
    })
    public void receiveTwo(String message) {
        System.out.println("messageTwo = " + message);
    }

}
