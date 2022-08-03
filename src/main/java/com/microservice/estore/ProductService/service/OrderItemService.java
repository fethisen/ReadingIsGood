package com.microservice.estore.ProductService.service;

import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface OrderItemService {

    /**
     * Get all the orderItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderItemDTO> findAllByOrderId(Pageable pageable, Long orderId);



    /**
     * Delete the "id" orderItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
