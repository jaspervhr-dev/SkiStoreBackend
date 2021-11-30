package com.pdmauto.skistorebackend.service.impl;

import com.pdmauto.skistorebackend.entity.Retailer;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import com.pdmauto.skistorebackend.service.RetailerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RetailerServiceImplTest {

    @Autowired
    RetailerService retailerService;

    @Autowired
    RetailerRepo retRepo;

    @Test
    void findByStoreName() {
        Retailer test = new Retailer();
        test.setUsername("impl test");
        test.setStorename("impl store search test");
        retRepo.save(test);
        assertNotNull(retailerService.findByStoreName("impl store search test"));
    }
}
