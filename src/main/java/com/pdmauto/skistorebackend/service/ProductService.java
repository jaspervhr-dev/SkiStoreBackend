package com.pdmauto.skistorebackend.service;

import com.pdmauto.skistorebackend.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findByProductName(String productName, String username);
    public List<Product> findByPrice(double price, String username);
    public List<Product> findByUser(String username);
}
