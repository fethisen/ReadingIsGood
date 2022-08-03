package com.microservice.estore.ProductService;

import com.microservice.estore.ProductService.domain.Authority;
import com.microservice.estore.ProductService.domain.User;
import com.microservice.estore.ProductService.repository.AuthorityRepository;
import com.microservice.estore.ProductService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProductServiceApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Insert/Load Data into H2 Database
     */
    @PostConstruct
    public void initUsers() {

        User userAdmin = new User();
        userAdmin.setId(1L);
        userAdmin.setLogin("admin");
        userAdmin.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        userAdmin.setFirstName("Administrator");
        userAdmin.setLastName("Administrator");
        userAdmin.setEmail("admin@localhost");
        userAdmin.setActivated(true);

        Authority authority = new Authority();
        authority.setName("ROLE_ADMIN");

        authority = authorityRepository.saveAndFlush(authority); // // TODO: Fethi use authorityrservice

        Authority roleCustomerAuth = new Authority();
        roleCustomerAuth.setName("ROLE_CUSTOMER");
        authorityRepository.save(roleCustomerAuth);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        userAdmin.setAuthorities(authorities);


        userRepository.save(userAdmin); // TODO: Fethi use userservice
    }

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
