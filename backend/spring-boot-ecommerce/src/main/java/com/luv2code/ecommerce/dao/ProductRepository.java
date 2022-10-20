package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//https://openhome.cc/Gossip/Spring/RepositoryRestResource.html
//collectionResourceRel = "product" 為 Name of JSON entry
//path = "path" 為 /product
@RepositoryRestResource(collectionResourceRel = "Product",path = "products")
public interface ProductRepository extends JpaRepository<Product,Long> {
}
