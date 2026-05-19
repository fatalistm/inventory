package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", unique = true, nullable = false)
    private Integer productId;

    private Integer quantity = 0;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime = LocalDateTime.now();
}
