package helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    // 生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        // 创建连接 mq 的连接工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接 rabbitmq 主机
//        connectionFactory.setHost("47.102.212.117");
        // 设置端口号
//        connectionFactory.setPort(5672);
        // 设置连接那个虚拟主机
//        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机用户名，密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");

        // 获取连接对象
//        Connection connection = connectionFactory.newConnection();

        // 通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 获取连接通道
        assert connection != null;
        Channel channel = connection.createChannel();

        // 通道绑定对应的消息队列
        // 参数1：队列名称 如果队列不存在则自动创建
        // 参数2：用来对应队列的特性是否持久化 true 持久化队列 / false 不持久化
        // 参数3：exclusive 是否独占队列 true 独占队列 / false 不独占
        // 参数4：autoDelete 是否在消费完成后自动删除队列 true 自动删除 / false 不自动删除
        // 参数5：额外参数
        channel.queueDeclare("aa", true, false, false,null);

        // 发布信息
        // 参数1：交换机名称 参数2：队列名称 参数3：传递消息额外设置 MessageProperties.PERSISTENT_TEXT_PLAIN （持久化消息） 参数4：消息对应具体内容
        channel.basicPublish("", "aa", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());

        // 关闭通道
//        channel.close();
//        // 关闭连接
//        connection.close();

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }

}
