package com.pdmauto.skistorebackend.service.impl;

import com.pdmauto.skistorebackend.entity.Retailer;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import com.pdmauto.skistorebackend.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom service implementation to allow for searching
 * user data through the store name
 */

@Service
public class RetailerServiceImpl implements RetailerService {

    @Autowired
    RetailerRepo retRepo;

    /**
     * @param name
     * @return a list of all stores with the matching name
     */
    @Override
    public List<Retailer> findByStoreName(String name) {
        List<Retailer> retList = retRepo.findAll();
        if(retList.isEmpty()) return null;
        List<Retailer> storesWithName = new ArrayList<>();
        for(Retailer ret : retList){
            if(ret.getStorename() == null) continue;
            if(ret.getStorename().equals(name)){
                storesWithName.add(ret);
            }
        }
        return storesWithName;
    }
}
