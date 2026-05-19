package com.example.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // 片段加载
    @GetMapping("/fragment/inventory")
    public String inventoryFragment() {
        return "fragments/inventory :: inventory";
    }

    @GetMapping("/fragment/inbound")
    public String inboundFragment() {
        return "fragments/inbound :: inbound";
    }

    @GetMapping("/fragment/outbound")
    public String outboundFragment() {
        return "fragments/outbound :: outbound";
    }
    @GetMapping("/fragment/saleOutbound")
    public String saleOutboundFragment() {
        return "fragments/saleOutbound :: saleOutbound";
    }

    @GetMapping("/fragment/profit")
    public String profitFragment() {
        return "fragments/profit :: profit";
    }

    @GetMapping("/fragment/productManage")
    public String productManageFragment() {
        return "fragments/productManage :: productManage";
    }

}