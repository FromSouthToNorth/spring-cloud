package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer2 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();

        // 每次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);

        // 参数1：队列名称 参数2：消息自动确认 true 消费者自动向 rabbitmq 确认消息消费 / false 不会自动确认消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));

                // 手动确认 参数1：手动确认标识 参数2：是否开启多个消息同时确认 false 每次确认一个 /  true 每次确认多个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }

}
