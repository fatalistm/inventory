package com.example.inventory.service;

import com.example.inventory.entity.*;
import com.example.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InboundService {

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private InboundDetailRepository inboundDetailRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockFlowRepository stockFlowRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public InboundOrder createInbound(String orderNo, String supplier, LocalDate inboundDate,
                                      String operator, String remark,
                                      List<InboundDetail> details) {
        // 1. 计算总金额并保存入库单主表
        BigDecimal total = details.stream()
                .map(d -> d.getAmount() != null ? d.getAmount() :
                        d.getPrice().multiply(BigDecimal.valueOf(d.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        InboundOrder order = new InboundOrder();
        order.setOrderNo(orderNo);
        order.setSupplier(supplier);
        order.setInboundDate(inboundDate);
        order.setTotalAmount(total);
        order.setOperator(operator);
        order.setRemark(remark);
        InboundOrder savedOrder = inboundOrderRepository.save(order);

        // 2. 处理每个明细：保存明细、更新库存、记录流水
        for (InboundDetail detail : details) {
            detail.setInboundOrderId(savedOrder.getId());
            if (detail.getAmount() == null && detail.getPrice() != null && detail.getQuantity() != null) {
                detail.setAmount(detail.getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
            }
            // 更新商品最新成本价
            inboundDetailRepository.save(detail);
            Product product = productRepository.findById(detail.getProductId()).orElse(null);
            if (product != null && detail.getPrice() != null) {
                product.setLastCostPrice(detail.getPrice());
                productRepository.save(product);
            }

            // 更新库存
            Optional<Inventory> invOpt = inventoryRepository.findByProductId(detail.getProductId());
            Inventory inventory = invOpt.orElseGet(() -> {
                Inventory newInv = new Inventory();
                newInv.setProductId(detail.getProductId());
                newInv.setQuantity(0);
                return newInv;
            });
            int before = inventory.getQuantity();
            int after = before + detail.getQuantity();
            inventory.setQuantity(after);
            inventory.setLastUpdateTime(LocalDateTime.now());
            inventoryRepository.save(inventory);

            // 记录流水
            StockFlow flow = new StockFlow();
            flow.setProductId(detail.getProductId());
            flow.setChangeType("入库");
            flow.setChangeQuantity(detail.getQuantity());
            flow.setBeforeQuantity(before);
            flow.setAfterQuantity(after);
            flow.setOrderNo(orderNo);
            flow.setRemark("入库单号：" + orderNo);
            stockFlowRepository.save(flow);

        }

        return savedOrder;

    }
}
