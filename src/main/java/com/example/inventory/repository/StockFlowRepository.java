package com.example.inventory.repository;

import com.example.inventory.entity.StockFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockFlowRepository extends JpaRepository<StockFlow, Integer> {
}
