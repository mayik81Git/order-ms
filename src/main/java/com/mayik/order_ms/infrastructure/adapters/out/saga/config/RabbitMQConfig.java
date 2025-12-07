package com.mayik.order_ms.infrastructure.adapters.out.saga.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * Configura el convertidor de mensajes JSON.
     * Es crucial para que los objetos de dominio (como Order) se serialicen y deserialicen correctamente.
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura el RabbitTemplate para usar el convertidor de mensajes JSON.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    // --- Declaración de Exchanges ---

    /**
     * Exchange principal para iniciar el Saga.
     */
    @Bean
    public TopicExchange orderSagaExchange() {
        return new TopicExchange("order-saga-exchange");
    }

    /**
     * Exchange para los comandos de compensación.
     */
    @Bean
    public TopicExchange orderCompensationExchange() {
        return new TopicExchange("order-compensation-exchange");
    }

    // --- Declaración de Colas ---

    /**
     * Cola para escuchar comandos de cancelación.
     */
    @Bean
    public Queue orderCancelQueue() {
        return new Queue("order-cancel-queue");
    }

    // --- Declaración de Bindings ---

    /**
     * Vincula la cola de cancelación al exchange de compensación.
     */
    @Bean
    public Binding bindingOrderCancel(Queue orderCancelQueue, TopicExchange orderCompensationExchange) {
        return BindingBuilder.bind(orderCancelQueue).to(orderCompensationExchange).with("order.cancel");
    }
}
