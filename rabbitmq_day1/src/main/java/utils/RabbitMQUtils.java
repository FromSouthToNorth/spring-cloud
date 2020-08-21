package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    private static final ConnectionFactory connectionFactory;

    static {
        // 重量级资源
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.102.212.117");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    // 定义提供连接对象的方法
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关闭通过连接
    public static void closeConnectionAndChanel(Channel channel, Connection connection) {
        try {
            if (channel !=  null)
                channel.close();
            if (connection != null)
                connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

}
