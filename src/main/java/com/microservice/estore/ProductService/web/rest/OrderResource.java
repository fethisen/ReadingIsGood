package com.microservice.estore.ProductService.web.rest;

import com.microservice.estore.ProductService.repository.OrderRepository;
import com.microservice.estore.ProductService.service.OrderService;
import com.microservice.estore.ProductService.service.dto.OrderDTO;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);



    private final OrderService orderService;

    private final OrderRepository orderRepository;

    public OrderResource(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    /**
     * {@code POST  /orders} : Create a new order.
     *
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDTO, or with status {@code 400 (Bad Request)} if the order has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<OrderItemDTO> orderItemDTOS) throws Exception {
        log.debug("REST request to save Order : {}", orderItemDTOS);
        OrderDTO result = orderService.save(orderItemDTOS);
        return ResponseEntity
            .created(new URI("/api/orders/" + result.getId()))
            .body(result);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the orderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        log.debug("REST request to get Order : {}", id);
        Optional<OrderDTO> orderDTO = orderService.findOne(id);
        return ResponseEntity.ok().body(orderDTO.get());
    }

    @GetMapping("/orders/strat-date/{startDate}/end-date/{endDate}")
    public ResponseEntity<List<OrderDTO>> getOrderByDateInterval(Pageable pageable,@PathVariable Date startDate, @PathVariable Date endDate) {
        log.debug("REST request to get Order by start-date: {} and end date: {}", startDate, endDate);
        Page<OrderDTO> orderDTO = orderService.findAllByDate(pageable,startDate,endDate);
        return ResponseEntity.ok().body(orderDTO.getContent());
    }

    @GetMapping("/orders/customer/{userId}")
    public ResponseEntity<List<OrderDTO>> getAllOrders(Pageable pageable,@PathVariable Long userId) {
        log.debug("REST request to get a page of Orders by userId: {}", userId);
        Page<OrderDTO> page = orderService.findAllByUserId(pageable,userId);
        return ResponseEntity.ok().body(page.getContent());
    }


    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the orderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws Exception {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
