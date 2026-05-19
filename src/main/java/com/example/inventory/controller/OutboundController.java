package com.example.inventory.controller;

import com.example.inventory.dto.SaleOutboundRequest;
import com.example.inventory.entity.OutboundDetail;
import com.example.inventory.entity.OutboundOrder;
import com.example.inventory.repository.OutboundOrderRepository;
import com.example.inventory.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/outbound")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    @Autowired
    private OutboundOrderRepository outboundOrderRepository;

    @PostMapping("/create")
    public OutboundOrder createOutbound(@RequestParam String orderNo,
                                        @RequestParam(required = false) String customer,
                                        @RequestParam String outboundDate,
                                        @RequestParam(required = false) String operator,
                                        @RequestParam(required = false) String remark,
                                        @RequestBody List<OutboundDetail> details) {
        LocalDate date = LocalDate.parse(outboundDate);
        return outboundService.createSaleOutbound(orderNo, customer, date, operator, remark, details);
    }
    @PostMapping("/sale")
    public OutboundOrder createSaleOutbound(@RequestBody SaleOutboundRequest request) {
        LocalDate date = LocalDate.parse(request.getOutboundDate());
        return outboundService.createSaleOutbound(
                request.getOrderNo(),
                request.getCustomer(),
                date,
                request.getOperator(),
                request.getRemark(),
                request.getDetails()
        );
    }
    @GetMapping("/list")
    public List<OutboundOrder> listOutboundOrders() {
        return outboundOrderRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }
}
