package com.pdmauto.skistorebackend.repo;

import com.pdmauto.skistorebackend.entity.Product;
import com.pdmauto.skistorebackend.entity.Retailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class productRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    RetailerRepo retailerRepo;

    @Test
    public void saveTest(){
        Retailer myRetailer = new Retailer();
        myRetailer.setUsername("jasper5");
        retailerRepo.save(myRetailer);
        Product pro = new Product();
        pro.setOwner(myRetailer);
        pro.setProductName("Ski1");
        productRepo.save(pro);
        assertNotNull(productRepo.getById(1));
    }

    @Nested
    class Delete {
        @BeforeEach
        public void init(){
            Product toBeDeleted = new Product();
            productRepo.save(toBeDeleted);
        }

        @Test
        public void deleteTest() {
            Product pro = productRepo.getById(2);
            productRepo.delete(pro);
            Optional<Product> nope = productRepo.findById(2);
            assertEquals(nope, Optional.empty());
        }
    }

    @Test
    public void updateTest(){
        Product myPro = new Product();
        myPro.setProductName("old");
        productRepo.save(myPro);
        myPro.setProductName("Twin Tips");
        productRepo.save(myPro);
        assertEquals(myPro.getProductName(), "Twin Tips");
    }

}
