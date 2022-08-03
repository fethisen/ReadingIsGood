package com.microservice.estore.ProductService.service.dto;

import com.microservice.estore.ProductService.domain.User;
import lombok.*;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String login;
}
