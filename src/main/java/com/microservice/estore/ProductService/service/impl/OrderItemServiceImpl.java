package com.microservice.estore.ProductService.service.impl;

import com.microservice.estore.ProductService.domain.OrderItem;
import com.microservice.estore.ProductService.repository.OrderItemRepository;
import com.microservice.estore.ProductService.service.OrderItemService;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import com.microservice.estore.ProductService.service.mapper.OrderItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final Logger log = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    private final OrderItemRepository orderItemRepository;

    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }


    @Override
    public Page<OrderItemDTO> findAllByOrderId(Pageable pageable, Long orderId) {
        return orderItemRepository.findByOrder_id(pageable,orderId).map(orderItemMapper::toDto);
    }



    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        orderItemRepository.deleteById(id);
    }
}
