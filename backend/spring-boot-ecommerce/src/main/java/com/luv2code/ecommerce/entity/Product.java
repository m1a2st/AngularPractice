package com.luv2code.ecommerce.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id" ,nullable = false)
    private ProductCategory category;

    @Column(name ="sku")
    private String sku;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="unit_price")
    private BigDecimal unitPrice;

    @Column(name ="image_url")
    private String imageUrl;

    @Column(name ="active")
    private boolean active;

    @Column(name ="unit_in_stock")
    private Integer unitsInStock;

    @Column(name ="date_create")
    //Hibernate will automatically manage the timestamps
    @CreationTimestamp
    private Date dateCreate;

    @Column(name ="last_update")
    //Hibernate will automatically manage the timestamps
    @UpdateTimestamp
    private Date lastUpdated;

    @Column(name = "category_id")
    private Integer categoryId;
}
