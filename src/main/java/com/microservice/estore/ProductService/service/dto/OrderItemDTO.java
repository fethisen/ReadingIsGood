package com.microservice.estore.ProductService.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class OrderItemDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private Double price;

    private OrderDTO order;

    private BookDTO book;
}
