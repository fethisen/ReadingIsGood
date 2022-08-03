package com.microservice.estore.ProductService.service.impl;

import com.microservice.estore.ProductService.domain.Order;
import com.microservice.estore.ProductService.domain.OrderItem;
import com.microservice.estore.ProductService.domain.enumeration.OrderStatus;
import com.microservice.estore.ProductService.repository.OrderRepository;
import com.microservice.estore.ProductService.service.OrderService;
import com.microservice.estore.ProductService.service.dto.OrderDTO;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import com.microservice.estore.ProductService.service.mapper.OrderItemMapper;
import com.microservice.estore.ProductService.service.mapper.OrderMapper;
import com.microservice.estore.ProductService.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public OrderDTO save(List<OrderItemDTO> orderItemDTOS) throws Exception {

        Double total = Double.valueOf(0);
        for (OrderItemDTO item : orderItemDTOS){
            if (item.getQuantity()<=0)
                throw new Exception("please check the quantity information");
            total += item.getPrice();
        }
        List<OrderItem> orderItemList = orderItemMapper.toEntity(orderItemDTOS);
        Order order = new Order();
        order.setOrderDate(Instant.now());
        order.setOrderStatus(OrderStatus.PAID);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalCost(total);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAllByDate(Pageable pageable, Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(pageable, startDate, endDate).map(orderMapper::toDto);
    }


    @Override
    public Page<OrderDTO> findAllByUserId(Pageable pageable,Long userId) {
        log.debug("Request to get all Orders by user id: {} ", userId);
        Page<Order> order = orderRepository.findByUser(pageable,userId);
        return orderRepository.findByUser(pageable,userId).map(orderMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id).map(orderMapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Request to delete Order : {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            order.get().setOrderStatus(OrderStatus.CANCEL);
            orderRepository.save(order.get());
        }else
             throw new Exception("No order record found in the given id information");
    }
}
