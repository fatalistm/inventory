package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "inbound_detail")
public class InboundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "inbound_order_id", nullable = false)
    private Integer inboundOrderId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal amount;
}
