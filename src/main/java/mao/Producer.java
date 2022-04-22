package mao;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import mao.tools.RabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Project name(项目名称)：rabbitMQ交换机之Fanout交换机
 * Package(包名): mao
 * Class(类名): Producer
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/22
 * Time(创建时间)： 19:33
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Producer
{
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException
    {
        Channel channel = RabbitMQ.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        for (int i = 0; i < 10; i++)
        {
            String message = "消息" + (i + 1);
            channel.basicPublish(EXCHANGE_NAME, "key", null, message.getBytes(StandardCharsets.UTF_8));
        }
        System.out.println("消息发送完成");
    }
}
