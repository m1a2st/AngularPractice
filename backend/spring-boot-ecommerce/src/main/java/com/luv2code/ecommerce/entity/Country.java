package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author
 * @Date
 * @Version
 * @Description
 */
@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name= "code")
    private String code;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    @JsonIgnore()
    private Set<State> state;

}
