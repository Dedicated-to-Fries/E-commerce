package com.example.ecommerce.TopicRabbitMq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: TopicRabbitConfig
 * @Author 杨金鹏
 * @Package com.example.ecommerce.TopicRabbitMq
 * @Date 2024/1/6 18:42
 */
@Configuration
public class TopicRabbitConfig {

    public final static String key="firstQueue";//设置队列1的名称
    private final static String SecondKey="secondQueue";//设置队列2的名称


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        //设置Json转换器
        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();//配置json转换器
    }

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicRabbitConfig.key);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(TopicRabbitConfig.SecondKey);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("TopicExchange");
    }

    @Bean
    public Binding bind(){
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(key);//将firstQueue与TopicExchange绑定，绑定键为key
    }

    @Bean
    public  Binding binding(){
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("#");//将secondQueue与TopicExchange绑定,#号代表只要发送消息,secondQueue()肯定能接收到消息.
        //总的来说就是路由键和secondQueue()绑定键肯定能匹配上,当发送消息时firstQueue和secondQueue都能发接收到消息
    }

}
