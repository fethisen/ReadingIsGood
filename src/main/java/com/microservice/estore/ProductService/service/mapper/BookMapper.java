package com.microservice.estore.ProductService.service.mapper;

import com.microservice.estore.ProductService.domain.Book;
import com.microservice.estore.ProductService.service.dto.BookDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface BookMapper extends EntityMapper<BookDTO, Book> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BookDTO toDtoId(Book book);
}
