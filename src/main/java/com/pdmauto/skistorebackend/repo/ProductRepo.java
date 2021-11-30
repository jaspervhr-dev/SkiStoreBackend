package com.pdmauto.skistorebackend.repo;

import com.pdmauto.skistorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
