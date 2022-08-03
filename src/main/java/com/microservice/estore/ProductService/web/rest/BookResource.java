package com.microservice.estore.ProductService.web.rest;

import com.microservice.estore.ProductService.security.AuthoritiesConstants;
import com.microservice.estore.ProductService.repository.BookRepository;
import com.microservice.estore.ProductService.service.BookService;
import com.microservice.estore.ProductService.service.dto.BookDTO;
import com.microservice.estore.ProductService.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;

    private final BookRepository bookRepository;

    public BookResource(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }


    @PostMapping("/books")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) throws Exception {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity
            .created(new URI("/api/books/" + result.getId()))
            .body(result);
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<BookDTO> updateBook(@PathVariable(value = "id", required = false) final Long id, @RequestBody BookDTO bookDTO)
        throws URISyntaxException {
        log.debug("REST request to update Book : {}, {}", id, bookDTO);
        if (bookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bookDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BookDTO result = bookService.save(bookDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code GET  /books/:id} : get the "id" book.
     *
     * @param id the id of the bookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<BookDTO> bookDTO = bookService.findOne(id);
        return ResponseEntity.ok().body(bookDTO.get());
    }
}
