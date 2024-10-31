package com.talissonmelo.btgpactual.listener.dto;

import java.util.List;

public record OrderCreatedEvent(Long orderId,
                                Long clientId,
                                List<OrderItemEvent> items) {
}
