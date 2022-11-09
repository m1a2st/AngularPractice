package com.luv2code.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author
 * @Date
 * @Version
 * @Description
 */
@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "image_url")
    private String imageUrl;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false,insertable = false,updatable = false)
    private Order order;

}
