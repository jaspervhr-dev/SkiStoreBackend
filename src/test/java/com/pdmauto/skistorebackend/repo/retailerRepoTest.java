package com.pdmauto.skistorebackend.repo;

import com.pdmauto.skistorebackend.entity.Retailer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class retailerRepoTest {

    @Autowired
    RetailerRepo retailerRepo;

    @Test
    public void saveTest(){
        Retailer myRetailer = new Retailer();
        myRetailer.setUsername("jasper1");
        myRetailer.setStorename("Gnar Shredders Inc");
        retailerRepo.save(myRetailer);
        assertNotNull(retailerRepo.findById("jasper1"));
    }

    @Nested
    class Delete {
        @BeforeEach
        public void init(){
            Retailer toBeDeleted = new Retailer();
            toBeDeleted.setUsername("jasper2");
        }

        @Test
        public void deleteTest() {
            Retailer myRetailer = retailerRepo.getById("jasper2");
            retailerRepo.delete(myRetailer);
            Optional<Retailer> nope = retailerRepo.findById("jasper2");
            assertEquals(nope, Optional.empty());
        }
    }

    @Test
    public void updateTest(){
        Retailer myRet = new Retailer();
        myRet.setUsername("jasper3");
        myRet.setFirstName("Jasper");
        retailerRepo.save(myRet);
        myRet.setFirstName("The New Jasper");
        retailerRepo.save(myRet);
        assertEquals(myRet.getFirstName(), "The New Jasper");
    }



}
