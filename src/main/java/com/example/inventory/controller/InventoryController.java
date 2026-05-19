package com.example.inventory.controller;

import com.example.inventory.entity.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/byProductId/{productId}")
    public Inventory getByProductId(@PathVariable Integer productId) {
        return inventoryRepository.findByProductId(productId).orElse(null);
    }

}
