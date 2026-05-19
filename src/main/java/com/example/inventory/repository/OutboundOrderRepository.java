package com.example.inventory.repository;

import com.example.inventory.entity.OutboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboundOrderRepository extends JpaRepository<OutboundOrder, Integer> {
}
