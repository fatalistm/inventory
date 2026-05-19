package com.example.inventory.dto;

import com.example.inventory.entity.OutboundDetail;
import java.util.List;

public class SaleOutboundRequest {
    private String orderNo;
    private String customer;
    private String outboundDate;
    private String operator;
    private String remark;
    private List<OutboundDetail> details;

    // Getters and setters
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    public String getOutboundDate() { return outboundDate; }
    public void setOutboundDate(String outboundDate) { this.outboundDate = outboundDate; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public List<OutboundDetail> getDetails() { return details; }
    public void setDetails(List<OutboundDetail> details) { this.details = details; }
}
