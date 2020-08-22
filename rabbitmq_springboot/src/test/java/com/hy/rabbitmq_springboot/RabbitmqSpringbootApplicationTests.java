package com.hy.rabbitmq_springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
class RabbitmqSpringbootApplicationTests {

    // 注册 rabbitmqTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void Topic() {
        rabbitTemplate.convertAndSend("topics", "user.save", "user.save 路由消息");
    }

    @Test
    void Routing() {
        rabbitTemplate.convertAndSend("directs", "error", "发送 error key Routing 模型");
    }

    @Test
    void Fanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout 模型");
    }

    @Test
    void Work() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work 模型" + i);
        }
    }

    // hello world
    @Test
    void helloWorld() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }



}
