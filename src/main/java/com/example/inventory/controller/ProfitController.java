package com.example.inventory.controller;

import com.example.inventory.entity.OutboundDetail;
import com.example.inventory.entity.OutboundOrder;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.OutboundDetailRepository;
import com.example.inventory.repository.OutboundOrderRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/profit")
public class ProfitController {

    @Autowired
    private OutboundOrderRepository outboundOrderRepository;

    @Autowired
    private OutboundDetailRepository outboundDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/statistics")
    public Map<String, Object> getProfitStatistics() {
        List<OutboundOrder> orders = outboundOrderRepository.findAll();
        BigDecimal totalProfit = BigDecimal.ZERO;
        List<Map<String, Object>> detailList = new ArrayList<>();

        for (OutboundOrder order : orders) {
            List<OutboundDetail> details = outboundDetailRepository.findByOutboundOrderId(order.getId());
            for (OutboundDetail detail : details) {
                Optional<Product> productOpt = productRepository.findById(detail.getProductId());
                if (!productOpt.isPresent()) continue;
                Product product = productOpt.get();
                // 成本价取商品的 last_cost_price，如果没有则默认为0
                BigDecimal costPrice = product.getLastCostPrice() != null ? product.getLastCostPrice() : BigDecimal.ZERO;
                BigDecimal salePrice = detail.getSalePrice() != null ? detail.getSalePrice() : BigDecimal.ZERO;
                BigDecimal quantity = BigDecimal.valueOf(detail.getQuantity());
                BigDecimal profit = salePrice.subtract(costPrice).multiply(quantity); // 净利润
                totalProfit = totalProfit.add(profit);

                Map<String, Object> item = new HashMap<>();
                item.put("orderNo", order.getOrderNo());
                item.put("customer", order.getCustomer());
                item.put("outboundDate", order.getOutboundDate());
                item.put("productName", product.getName());
                item.put("sku", product.getSku());
                item.put("quantity", detail.getQuantity());
                item.put("costPrice", costPrice);
                item.put("salePrice", salePrice);
                item.put("profit", profit);   // 这里存储的是利润
                detailList.add(item);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalProfit", totalProfit);
        result.put("details", detailList);
        return result;
    }
}
