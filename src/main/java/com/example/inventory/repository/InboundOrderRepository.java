package com.example.inventory.repository;

import com.example.inventory.entity.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Integer> {
}
