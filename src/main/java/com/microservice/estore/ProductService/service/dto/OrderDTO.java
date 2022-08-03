package com.microservice.estore.ProductService.service.dto;

import com.microservice.estore.ProductService.domain.enumeration.OrderStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Data
public class OrderDTO implements Serializable {

    private Long id;

    private Instant orderDate;

    private Double totalCost;

    private OrderStatus orderStatus;

    private String firstName;

    private String lastName;

}
