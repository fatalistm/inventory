package com.example.inventory.repository;

import com.example.inventory.entity.InboundDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundDetailRepository extends JpaRepository<InboundDetail, Integer> {
}
