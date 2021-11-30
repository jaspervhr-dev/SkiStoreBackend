package com.pdmauto.skistorebackend.controller;

import com.pdmauto.skistorebackend.entity.Product;
import com.pdmauto.skistorebackend.entity.Retailer;
import com.pdmauto.skistorebackend.repo.ProductRepo;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Auth Jasper Rodgers
 * Rest Controller for inventory entity related endpoints
 */

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    RetailerRepo retRepo;

    @Autowired
    ProductRepo prodRepo;

    /**
     * @param username
     * @return http status and message about if user was successfully created
     */
    @PostMapping("/createproduct")
    public ResponseEntity<?> newProduct(@RequestParam(name = "username") String username){
        Optional<Retailer> owner = retRepo.findById(username);
        if(!owner.isPresent()){
            return new ResponseEntity<>("Error: username not recognized", HttpStatus.BAD_REQUEST);
        }
        Retailer exists = retRepo.getById(username);
        Product newProduct = new Product();
        newProduct.setOwner(exists);
        prodRepo.save(newProduct);
        return new ResponseEntity<>("Product created", HttpStatus.CREATED);
    }

    /**
     * @param productName
     * @param productId
     * @return http status and message about the success of the update
     */
    @PutMapping("/nameproduct")
    public ResponseEntity<?> newName(@RequestParam(name = "productName") String productName, @RequestParam(name = "productId") int productId){
        Optional<Product> currProduct = prodRepo.findById(productId);
        if(!currProduct.isPresent()){
            return new ResponseEntity<>("Error: product id not recognized", HttpStatus.BAD_REQUEST);
        }
        Product exists = prodRepo.getById(productId);
        exists.setProductName(productName);
        prodRepo.save(exists);
        return new ResponseEntity<>("Name updated", HttpStatus.OK);
    }

    /**
     * @param price
     * @param productId
     * @return http status and message about the success of the update
     */
    @PutMapping("/setprice")
    public ResponseEntity<?> newPrice(@RequestParam(name = "price") long price, @RequestParam(name = "productId") int productId){
        Optional<Product> currProduct = prodRepo.findById(productId);
        if(!currProduct.isPresent()){
            return new ResponseEntity<>("Error: product id not recognized", HttpStatus.BAD_REQUEST);
        }
        Product exists = prodRepo.getById(productId);
        exists.setPrice(price);
        prodRepo.save(exists);
        return new ResponseEntity<>("Price updated", HttpStatus.OK);
    }

    /**
     * @param quantity
     * @param productId
     * @return http status and message about the success of the update
     */
    @PutMapping("/setquantity")
    public ResponseEntity<?> newQuantity(@RequestParam(name = "quantity") int quantity, @RequestParam(name = "productId") int productId){
        Optional<Product> currProduct = prodRepo.findById(productId);
        if(!currProduct.isPresent()){
            return new ResponseEntity<>("Error: product id not recognized", HttpStatus.BAD_REQUEST);
        }
        Product exists = prodRepo.getById(productId);
        exists.setQuantity(quantity);
        prodRepo.save(exists);
        return new ResponseEntity<>("Quantity updated", HttpStatus.OK);
    }

    /**
     * @param productId
     * @return http status and message about the success of the deletion
     */
    @DeleteMapping("/deleteproduct")
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "productId") int productId){
        Optional<Product> currProduct = prodRepo.findById(productId);
        if(!currProduct.isPresent()){
            return new ResponseEntity<>("Error: product id not recognized", HttpStatus.BAD_REQUEST);
        }
        Product exists = prodRepo.getById(productId);
        prodRepo.delete(exists);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    /**
     * @param productId
     * @return the product entity or an error message and http status
     */
    @GetMapping("getproduct")
    public ResponseEntity<?> getProduct(@RequestParam(name = "productId") int productId){
        Optional<Product> currProduct = prodRepo.findById(productId);
        if(!currProduct.isPresent()){
            return new ResponseEntity<>("Error: product id not recognized", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(currProduct, HttpStatus.OK);
    }
}
