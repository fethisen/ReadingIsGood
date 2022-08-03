package com.microservice.estore.ProductService.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Data
public class BookDTO implements Serializable {

    private Long id;

    private String name;

    private String author;

    private Double price;

    private Integer stock;

    private String description;

    private String category;

    private Instant createTime;

    private Instant updateTime;

}
