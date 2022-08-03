package com.microservice.estore.ProductService.web.rest;

import com.microservice.estore.ProductService.repository.OrderItemRepository;
import com.microservice.estore.ProductService.service.OrderItemService;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderItemResource {

    private final Logger log = LoggerFactory.getLogger(OrderItemResource.class);


    private final OrderItemService orderItemService;

    private final OrderItemRepository orderItemRepository;

    public OrderItemResource(OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * {@code GET  /order-items} : get all the orderItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderItems in body.
     */
    @GetMapping("/order-items/order/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems(Pageable pageable,@PathVariable Long orderId) {
        log.debug("REST request to get a page of OrderItems by order id: {}", orderId);
        Page<OrderItemDTO> page = orderItemService.findAllByOrderId(pageable,orderId);
        return ResponseEntity.ok().body(page.getContent());
    }



    /**
     * {@code DELETE  /order-items/:id} : delete the "id" orderItem.
     *
     * @param id the id of the orderItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        log.debug("REST request to delete OrderItem : {}", id);
        orderItemService.delete(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}
