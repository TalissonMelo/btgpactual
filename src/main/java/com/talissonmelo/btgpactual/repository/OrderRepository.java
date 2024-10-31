package com.talissonmelo.btgpactual.repository;

import com.talissonmelo.btgpactual.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
