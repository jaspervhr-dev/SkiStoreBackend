package com.pdmauto.skistorebackend.service;

import com.pdmauto.skistorebackend.entity.Retailer;

import java.util.List;

public interface RetailerService {
    public List<Retailer> findByStoreName(String name);
}
