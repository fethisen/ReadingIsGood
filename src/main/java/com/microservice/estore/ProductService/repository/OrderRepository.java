package com.microservice.estore.ProductService.repository;

import com.microservice.estore.ProductService.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUser(Pageable pageable, Long userId);
    Page<Order> findByOrderDateBetween(Pageable pageable,Date startDate, Date endDate);
}
