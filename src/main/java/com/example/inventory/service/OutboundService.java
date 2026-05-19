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
public class OutboundService {

    @Autowired
    private OutboundOrderRepository outboundOrderRepository;

    @Autowired
    private OutboundDetailRepository outboundDetailRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StockFlowRepository stockFlowRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OutboundOrder createSaleOutbound(String orderNo, String customer, LocalDate outboundDate,
                                            String operator, String remark,
                                            List<OutboundDetail> details) {
        // 1. 校验库存是否足够（根据商品ID查询库存）
        for (OutboundDetail detail : details) {
            Optional<Inventory> invOpt = inventoryRepository.findByProductId(detail.getProductId());
            if (!invOpt.isPresent()) {
                throw new RuntimeException("商品ID " + detail.getProductId() + " 没有库存记录，请先入库");
            }
            Inventory inventory = invOpt.get();
            if (inventory.getQuantity() < detail.getQuantity()) {
                throw new RuntimeException("商品ID " + detail.getProductId() + " 库存不足，当前库存：" + inventory.getQuantity());
            }
        }

        // 2. 创建出库单主表
        OutboundOrder order = new OutboundOrder();
        order.setOrderNo(orderNo);
        order.setCustomer(customer);
        order.setOutboundDate(outboundDate);
        order.setOperator(operator);
        order.setRemark(remark);
        OutboundOrder savedOrder = outboundOrderRepository.save(order);

        // 3. 处理每个明细：保存明细、扣减库存、记录流水
        for (OutboundDetail detail : details) {
            // 如果明细中没有提供销售价，则从商品表获取
            if (detail.getSalePrice() == null || detail.getSalePrice().compareTo(BigDecimal.ZERO) == 0) {
                Product product = productRepository.findById(detail.getProductId())
                        .orElseThrow(() -> new RuntimeException("商品不存在，ID：" + detail.getProductId()));
                detail.setSalePrice(product.getSalePrice() != null ? product.getSalePrice() : BigDecimal.ZERO);
            }
            detail.setOutboundOrderId(savedOrder.getId());
            outboundDetailRepository.save(detail);

            // 扣减库存
            Inventory inventory = inventoryRepository.findByProductId(detail.getProductId()).get();
            int before = inventory.getQuantity();
            int after = before - detail.getQuantity();
            inventory.setQuantity(after);
            inventory.setLastUpdateTime(LocalDateTime.now());
            inventoryRepository.save(inventory);

            // 记录流水
            StockFlow flow = new StockFlow();
            flow.setProductId(detail.getProductId());
            flow.setChangeType("销售出库");
            flow.setChangeQuantity(detail.getQuantity());
            flow.setBeforeQuantity(before);
            flow.setAfterQuantity(after);
            flow.setOrderNo(orderNo);
            flow.setRemark("销售出库");
            stockFlowRepository.save(flow);
        }


        return savedOrder;
    }
}
