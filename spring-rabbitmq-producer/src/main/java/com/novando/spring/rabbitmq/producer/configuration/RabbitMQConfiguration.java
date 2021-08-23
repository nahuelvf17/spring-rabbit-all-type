package com.novando.spring.rabbitmq.producer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue queueA(){
        return new Queue("queue.A", false);
    }

    @Bean
    Queue queueB(){
        return new Queue("queue.B", false);
    }

    @Bean
    Queue allQueue(){
        return new Queue("queue.all", false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    Binding binding(Queue queueA, TopicExchange topicExchange){
        return BindingBuilder
                .bind(queueA)
                .to(exchange())
                .with(ROUTING_A);
    }

    @Bean
    Binding bindingB(Queue queueB, TopicExchange topicExchange){
        return BindingBuilder
                .bind(queueB)
                .to(exchange())
                .with(ROUTING_B);
    }

    @Bean
    Binding bindingAll(Queue allQueue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(allQueue)
                .to(exchange())
                .with(ROUTING_ALL);
    }
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    public static final String ROUTING_ALL = "routing.*";

    public static final String EXCHANGE_DIRECT = "exchange.direct";
    public static final String EXCHANGE_FANOUT = "exchange.fanout";
    public static final String EXCHANGE_TOPIC = "exchange.topic";
}
