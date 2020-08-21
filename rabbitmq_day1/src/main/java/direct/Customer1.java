package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Customer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        assert connection != null;
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        // 通道声明交换机以及交换机类型
        channel.exchangeDeclare(exchangeName, "direct");

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于 route key 绑定队列和交换机
        channel.queueBind(queue, "logs_direct", "error");

        // 获取消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }

}
