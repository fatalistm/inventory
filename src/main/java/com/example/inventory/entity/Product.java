package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String sku;

    @Column(nullable = false, length = 100)
    private String name;

    private String brand;
    private String model;
    private String spec;
    private String unit = "台";
    private BigDecimal price;

    @Column(name = "alert_quantity")
    private Integer alertQuantity = 10;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "update_time")
    private LocalDateTime updateTime = LocalDateTime.now();
    @Column(name = "sale_price")
    private BigDecimal salePrice = BigDecimal.ZERO;
    @Column(name = "last_cost_price")
    private BigDecimal lastCostPrice = BigDecimal.ZERO;
}
