package xxm.rabbitmq.code;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Data;
import org.junit.Test;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class Send {
    @Test
    public void connection() {
        Channel channel = null;
        Connection conn = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            // "guest"/"guest" by default, limited to localhost connections
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setVirtualHost("/");
            factory.setHost("49.234.25.12");
            factory.setPort(5672);

            conn = factory.newConnection();

            //开启通道
            channel = conn.createChannel();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                channel.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("-----关闭失败----");
            }

        }


    }

}

@Data
class User {
    String name;
    String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

}