package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "outbound_detail")
public class OutboundDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "outbound_order_id", nullable = false)
    private Integer outboundOrderId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    private Integer quantity;

    @Column(name = "sale_price")
    private BigDecimal salePrice;
}
