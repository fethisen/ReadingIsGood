package com.microservice.estore.ProductService.service;

import com.microservice.estore.ProductService.service.dto.BookDTO;

import java.util.Optional;

public interface BookService {
    /**
     * Save a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    BookDTO save(BookDTO bookDTO);

    /**
     * Get the "id" book.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookDTO> findOne(Long id);


}
