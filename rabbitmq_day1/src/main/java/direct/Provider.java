package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        assert connection != null;
        String exchangeName = "logs_direct";

        // 获取连接通道对象
        Channel channel = connection.createChannel();

        // 通过通道声明交换机 参数1：交换机名称  参数2：direct 路由模式
        channel.exchangeDeclare(exchangeName, "direct");
        // 发送消息
        String routingKey = "info";
        channel.basicPublish(exchangeName, routingKey, null,("这是 direct 模式发布的基于 route key：[" + routingKey + "] 发送的消息").getBytes());
        // 关闭资源
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }

}
