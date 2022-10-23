package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//https://openhome.cc/Gossip/Spring/RepositoryRestResource.html
//collectionResourceRel = "product" 為 Name of JSON entry
//path = "path" 為 /product
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Spring Data REST automatically expose endpoint
    //http://localhost:8080/api/product/search/findByCategoryId?id=2
    public Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
}
