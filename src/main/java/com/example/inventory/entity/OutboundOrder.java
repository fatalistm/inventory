package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "outbound_order")
public class OutboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_no", unique = true, nullable = false, length = 50)
    private String orderNo;

    private String customer;

    @Column(name = "outbound_date")
    private LocalDate outboundDate;

    private String operator;
    private String remark;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();
}
