package com.pdmauto.skistorebackend.repo;

import com.pdmauto.skistorebackend.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRepo extends JpaRepository<Retailer,String> {
}
