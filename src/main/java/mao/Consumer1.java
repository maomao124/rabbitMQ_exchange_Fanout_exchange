package mao;

import com.rabbitmq.client.*;
import mao.tools.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Project name(项目名称)：rabbitMQ交换机之Fanout交换机
 * Package(包名): mao
 * Class(类名): Consumer1
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 19:34
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Consumer1
{
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException
    {
        Channel channel = RabbitMQ.getChannel();

        String queue = channel.queueDeclare().getQueue();
        System.out.println(queue);
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //绑定交换机
        channel.queueBind(queue, EXCHANGE_NAME, "key");
        //接收消息
        channel.basicConsume(queue, true, new DeliverCallback()
        {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException
            {
                byte[] messageBody = message.getBody();
                String message1 = new String(messageBody, StandardCharsets.UTF_8);
                System.out.println("消费者1接收消息：" + message1);
            }
        }, new CancelCallback()
        {
            @Override
            public void handle(String consumerTag) throws IOException
            {
                System.out.println("异常");
            }
        });
    }
}
