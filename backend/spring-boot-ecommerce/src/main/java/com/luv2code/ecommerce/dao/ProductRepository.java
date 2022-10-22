package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//https://openhome.cc/Gossip/Spring/RepositoryRestResource.html
//collectionResourceRel = "product" 為 Name of JSON entry
//path = "path" 為 /products
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "product",path = "product")
public interface ProductRepository extends JpaRepository<Product,Long> {
}
