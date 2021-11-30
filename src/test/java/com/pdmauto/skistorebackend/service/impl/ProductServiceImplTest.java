package com.pdmauto.skistorebackend.service.impl;

import com.pdmauto.skistorebackend.entity.Product;
import com.pdmauto.skistorebackend.entity.Retailer;
import com.pdmauto.skistorebackend.repo.ProductRepo;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import com.pdmauto.skistorebackend.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    RetailerRepo retailerRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductService proService;

    @BeforeEach
    public void init(){
        Retailer newRet = new Retailer();
        newRet.setUsername("impl test");
        retailerRepo.save(newRet);
        Product newPro = new Product();
        newPro.setProductName("Atomic");
        newPro.setOwner(newRet);
        newPro.setQuantity(100);
        newPro.setPrice(500.99);
        productRepo.save(newPro);
    }

    @Test
    void findByPrice() {
        assertNotNull(proService.findByPrice(500.99, "impl test"));
    }

    @Test
    void findByProductName() {
        assertNotNull(proService.findByProductName("Atomic", "impl test"));
    }

    @Test
    void findByUser() {
        assertNotNull(proService.findByUser("impl test"));
    }
}
