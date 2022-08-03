package com.microservice.estore.ProductService.service.mapper;

import com.microservice.estore.ProductService.domain.Order;
import com.microservice.estore.ProductService.service.dto.OrderDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderDTO toDtoId(Order order);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "user.firstName")
    OrderDTO toDto(Order order);
}
