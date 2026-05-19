package com.example.inventory.repository;

import com.example.inventory.entity.OutboundDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutboundDetailRepository extends JpaRepository<OutboundDetail, Integer> {
    List<OutboundDetail> findByOutboundOrderId(Integer outboundOrderId);
}
