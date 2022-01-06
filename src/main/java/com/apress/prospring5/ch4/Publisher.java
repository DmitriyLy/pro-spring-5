package com.apress.prospring5.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class Publisher implements ApplicationContextAware {
    private ApplicationContext context;

    @Configuration
    static class MessageEventConfig {

        @Bean
        public Publisher publisher() {
            return new Publisher();
        }

        @Bean
        public MessageEventListener messageEventListener() {
            return new MessageEventListener();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public void publish(String message) {
        context.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(MessageEventConfig.class);
        Publisher publisher = context.getBean("publisher", Publisher.class);

        publisher.publish("I send an SOS to the world... ");
        publisher.publish("... I hope that someone gets my...");
        publisher.publish("... Message in a bottle");

        context.close();
    }
}
