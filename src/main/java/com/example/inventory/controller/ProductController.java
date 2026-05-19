package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> list() {
        return productService.listAll();
    }

    // 根据id查询单个商品
    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    // 添加商品
    @PostMapping("/add")
    public Product add(@RequestBody Product product) {
        // 设置默认值
        if (product.getCreateTime() == null) product.setCreateTime(LocalDateTime.now());
        if (product.getUpdateTime() == null) product.setUpdateTime(LocalDateTime.now());
        if (product.getUnit() == null) product.setUnit("台");
        if (product.getAlertQuantity() == null) product.setAlertQuantity(10);
        if (product.getSalePrice() == null) product.setSalePrice(BigDecimal.ZERO);
        if (product.getLastCostPrice() == null) product.setLastCostPrice(BigDecimal.ZERO);
        return productService.save(product);
    }

    // 更新商品
    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        product.setUpdateTime(LocalDateTime.now());
        return productService.save(product);
    }

    // 删除商品
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteById(id);
    }



    @GetMapping("/find/{sku}")
    public Product findBySku(@PathVariable String sku) {
        return productService.findBySku(sku);
    }
}
