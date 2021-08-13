package com.gokhand.roomwebapp.config;

import com.gokhand.roomwebapp.async.RoomCleanerListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AsyncConfig {
    private static final String QUE_name = "room cleaner";
    private static final String Exchange_name = "operations";

    @Bean
    public Queue queue(){
        return new Queue(QUE_name, false);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Exchange_name);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("landon.rooms.cleaner");

    }
    @Bean
    public MessageListenerAdapter listenerAdapter(RoomCleanerListener listener) {
        return new MessageListenerAdapter(listener, "receiveMessage");
    }
    @Bean
    public SimpleMessageListenerContainer container
            (ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container =  new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUE_name);
        container.setMessageListener(listenerAdapter);
        return container;

    }

}