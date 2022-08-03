package com.microservice.estore.ProductService.service;

import com.microservice.estore.ProductService.service.dto.OrderDTO;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface OrderService {
    /**
     * Save a order.
     *
     * @return the persisted entity.
     */
    OrderDTO save(List<OrderItemDTO> orderItemDTOS) throws Exception;

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderDTO> findAllByDate(Pageable pageable, Date startDate, Date endDate);

    Page<OrderDTO> findAllByUserId(Pageable pageable, Long userId);

    /**
     * Get the "id" order.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderDTO> findOne(Long id);

    /**
     * Delete the "id" order.
     *
     * @param id the id of the entity.
     */
    void delete(Long id) throws Exception;
}
