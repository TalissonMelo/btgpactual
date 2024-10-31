package com.talissonmelo.btgpactual.service;

import com.talissonmelo.btgpactual.domain.Item;
import com.talissonmelo.btgpactual.domain.Order;
import com.talissonmelo.btgpactual.listener.dto.OrderCreatedEvent;
import com.talissonmelo.btgpactual.repository.OrderRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository,
                        MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreatedEvent event) {

        var entity = new Order();

        entity.setOrderId(event.orderId());
        entity.setCustomerId(event.clientId());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);

    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.items()
                .stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.amount())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<Item> getOrderItems(OrderCreatedEvent event) {
        return event.items().stream()
                .map(i -> new Item(i.product(), i.amount(), i.price()))
                .toList();
    }
}