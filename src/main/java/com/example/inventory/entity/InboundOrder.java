package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inbound_order")
public class InboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_no", unique = true, nullable = false, length = 50)
    private String orderNo;

    private String supplier;

    @Column(name = "inbound_date")
    private LocalDate inboundDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private String operator;
    private String remark;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();
}
