package com.talissonmelo.btgpactual.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(String product,
                             Integer amount,
                             BigDecimal price) {
}
