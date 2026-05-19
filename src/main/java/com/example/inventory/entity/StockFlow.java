package com.example.inventory.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_flow")
public class StockFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "change_type", length = 20)
    private String changeType;   // 入库 / 出库 / 盘盈 / 盘亏

    @Column(name = "change_quantity")
    private Integer changeQuantity;

    @Column(name = "before_quantity")
    private Integer beforeQuantity;

    @Column(name = "after_quantity")
    private Integer afterQuantity;

    @Column(name = "order_no", length = 50)
    private String orderNo;      // 关联的入库单号或出库单号

    private String remark;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();
}
