package br.com.dio.warehouse.service.impl;

import br.com.dio.warehouse.dto.StockStatusMessage;
import br.com.dio.warehouse.service.IProductChangeAvaliabilityProducer;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ProductChangeAvailabilityProducerImpl implements IProductChangeAvaliabilityProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKeyName;

    public ProductChangeAvailabilityProducerImpl(RabbitTemplate rabbitTemplate,
                                                 @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                 String exchangeName,
                                                 @Value("${spring.rabbitmq.exchange.product-change-availability}")
                                                 String routingKeyName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKeyName = routingKeyName;
    }

    @Override
    public void notifyStatusChange(StockStatusMessage message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}
