package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {

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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-1：" + new String(body));

                // 参数1：确认队列中那个消息；参数2：是否开启多个消息同时确认 false 每次确认一个 /  true 每次确认多个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }

}
