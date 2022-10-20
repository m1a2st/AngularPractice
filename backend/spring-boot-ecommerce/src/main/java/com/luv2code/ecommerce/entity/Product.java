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

    /* com.luv2code.ecommerce.entity.Product column: category_id(should be mapped with insert="false" update="false")
     * 錯誤原因：由於同一個元素注解了兩次，所有報錯
     * 解決方法：1.刪除一個注解元素 2.將其中一個具有映射關系的元素設置為只讀，insertable = false, updatable = false
     */
    @ManyToOne
    @JoinColumn(name = "category_id" ,nullable = false,insertable = false,updatable = false)
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

    @Column(name ="units_in_stock")
    private Integer unitsInStock;

    @Column(name ="date_created")
    //Hibernate will automatically manage the timestamps
    @CreationTimestamp
    private Date dateCreated;

    @Column(name ="last_updated")
    //Hibernate will automatically manage the timestamps
    @UpdateTimestamp
    private Date lastUpdated;

    @Column(name = "category_id")
    private Integer categoryId;
}
