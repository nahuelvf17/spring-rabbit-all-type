package com.novando.spring.rabbitmq.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
    private int id;
    private String name;
}
