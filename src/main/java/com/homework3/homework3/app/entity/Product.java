package com.homework3.homework3.app.entity;

import com.homework3.homework3.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(generator = "Product")
    @SequenceGenerator(name = "Product" , sequenceName = "PRODUCT_ID_SEQ")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PRICE", precision = 19, scale = 2)
    private BigDecimal price;
}
