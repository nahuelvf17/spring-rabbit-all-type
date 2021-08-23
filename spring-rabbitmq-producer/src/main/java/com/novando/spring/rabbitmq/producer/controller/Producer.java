package com.novando.spring.rabbitmq.producer.controller;

import com.novando.spring.rabbitmq.producer.model.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange exchange;

    @PostMapping("/post")
    public String send(@RequestBody  Message message){
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
        return "Mensanje enviado correctamente con topic";
    }
}
