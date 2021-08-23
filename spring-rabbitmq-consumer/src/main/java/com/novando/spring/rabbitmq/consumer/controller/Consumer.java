package com.novando.spring.rabbitmq.consumer.controller;

import com.novando.spring.rabbitmq.consumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "queue.A")
    private void receive(Message message){
        log.info("Mensaje recibido desde QUEUE A-{}", message);
    }

    @RabbitListener(queues = "queue.B")
    private void receiveFromB(Message message){
        log.info("Mensaje recibido desde QUEUE B-{}", message);
    }

    @RabbitListener(queues = "queue.all")
    private void receiveFromAll(Message message){
        log.info("Mensaje recibido desde QUEUE all-{}", message);
    }
}
