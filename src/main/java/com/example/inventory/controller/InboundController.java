package com.example.inventory.controller;

import com.example.inventory.entity.InboundDetail;
import com.example.inventory.entity.InboundOrder;
import com.example.inventory.repository.InboundOrderRepository;
import com.example.inventory.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inbound")
public class InboundController {

    @Autowired
    private InboundService inboundService;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;   // 添加这个用于列表查询

    @PostMapping("/create")
    public InboundOrder createInbound(@RequestParam String orderNo,
                                      @RequestParam(required = false) String supplier,
                                      @RequestParam String inboundDate,
                                      @RequestParam(required = false) String operator,
                                      @RequestParam(required = false) String remark,
                                      @RequestBody List<InboundDetail> details) {
        LocalDate date = LocalDate.parse(inboundDate);
        return inboundService.createInbound(orderNo, supplier, date, operator, remark, details);
    }

    @GetMapping("/list")
    public List<InboundOrder> listInboundOrders() {
        return inboundOrderRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }
}
