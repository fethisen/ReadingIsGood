package com.microservice.estore.ProductService.service.mapper;

import com.microservice.estore.ProductService.domain.OrderItem;
import com.microservice.estore.ProductService.service.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link OrderItem} and its DTO {@link OrderItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { OrderMapper.class, BookMapper.class })
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {
    @Mapping(target = "order", source = "order", qualifiedByName = "id")
    @Mapping(target = "book", source = "book", qualifiedByName = "id")
    OrderItemDTO toDto(OrderItem s);
}
