package com.luv2code.ecommerce.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author
 * @Date
 * @Version
 * @Description
 */
@Entity
@Table(name = "state")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = false,insertable = false,updatable = false)
    private Country country;

}
