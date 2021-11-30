package com.pdmauto.skistorebackend.service.impl;


import com.pdmauto.skistorebackend.entity.Product;
import com.pdmauto.skistorebackend.repo.ProductRepo;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import com.pdmauto.skistorebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Custom service implementation to allow to for searching
 * through the product data via the price, product name and username
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo invRepo;

    @Autowired
    RetailerRepo retRepo;

    /**
     * @param price
     * @param username
     * @return a list of product entities, owned by the user with the matching price
     */
    @Override
    public List<Product> findByPrice(double price, String username) {
        List<Product> userProd = findByUser(username);
        if(userProd == null) return null;
        List<Product> costProd = new ArrayList<>();
        for(Product pro : userProd){
            if(pro.getPrice() == price) {
                costProd.add(pro);
            }
        }
        return costProd;
    }

    /**
     * @param productName
     * @param username
     * @return a list of product entities owned by the user, with the given product name (duplicates are allowed)
     */
    @Override
    public List<Product> findByProductName(String productName, String username) {
        List<Product> userProd = findByUser(username);
        if(userProd == null) return null;
        List<Product> nameProd = new ArrayList<Product>();
        for(Product pro : userProd){
            if(pro.getProductName().equals(productName)) {
                nameProd.add(pro);
            }
        }
        return nameProd;
    }

    /**
     * @param username
     * @return a list of all product entities owned by the user
     */
    @Override
    public List<Product> findByUser(String username) {
        List<Product> fullProd = invRepo.findAll();
        if(fullProd.isEmpty()) return null;
        List<Product> usersProd = new ArrayList<Product>();
        for(Product pro : fullProd){
            if(pro.getOwner() == null) continue;
            else if(pro.getOwner().getUsername().equals(username)){
                usersProd.add(pro);
            }
        }
        return usersProd;
    }
}
