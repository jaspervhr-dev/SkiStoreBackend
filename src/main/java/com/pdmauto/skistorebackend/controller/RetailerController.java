package com.pdmauto.skistorebackend.controller;

import com.pdmauto.skistorebackend.entity.Retailer;
import com.pdmauto.skistorebackend.repo.RetailerRepo;
import com.pdmauto.skistorebackend.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Auth Jasper Rodgers
 * Rest Controller for retailer entity related endpoints
 */

@RestController
@RequestMapping("/retailer")
public class RetailerController {

    @Autowired
    RetailerRepo retRepo;

    @Autowired
    RetailerService retService;

    /**
     * @param username
     * @return new user entity or error message
     */
    @PostMapping("/createuser")
    public ResponseEntity<?> registerWithUsername(@RequestParam(name = "username") String username){
        if(username.equals("")){
            return new ResponseEntity<>("Error: Username is empty.", HttpStatus.BAD_REQUEST);
        }
        if(!retRepo.findById(username).equals(Optional.empty())){
            return new ResponseEntity<>("Error: Username already exists.", HttpStatus.BAD_REQUEST);
        }
        Retailer newUser = new Retailer();
        newUser.setUsername(username);
        retRepo.save(newUser);
        return new ResponseEntity<>("Retailer created.", HttpStatus.CREATED);
    }

    /**
     * @param username
     * @return user entity with given username
     */
    @GetMapping("/getuser")
    public ResponseEntity<?> findUser(@RequestParam(name = "username") String username){
        Optional<Retailer> foundUser = retRepo.findById(username);
        if(!foundUser.isPresent()){
            return new ResponseEntity<>("Error: username not recognized", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    /**
     * @param storeName
     * @return list of all stores with matching name
     */
    @GetMapping("/storesearch")
    public ResponseEntity<?> search(@RequestParam(name = "storename") String storeName){
        List<Retailer> storesWithName = new ArrayList<>();
        List<Retailer> allStores = retService.findByStoreName(storeName);
        if(allStores == null){
            return new ResponseEntity<>("Error: Store name not recognized", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(allStores, HttpStatus.OK);
    }

    /**
     * @param username
     * @return Http status whether delete was successful or not
     */
    @DeleteMapping("/deleteaccount")
    public ResponseEntity<?> deleteAccount(@RequestParam(name = "username") String username){
        Optional<Retailer> foundRet = retRepo.findById(username);
        if(!foundRet.isPresent()){
            return new ResponseEntity<>("Error: Username not recognized", HttpStatus.BAD_REQUEST);
        }
        Retailer exists = retRepo.getById(username);
        retRepo.delete(exists);
        return new ResponseEntity<>("Account deleted.", HttpStatus.OK);
    }

    /**
     * @param newStoreName
     * @param username
     * @return the updated entity or error message
     */
    @PutMapping("/newstorename")
    public ResponseEntity<?> newStoreName(@RequestParam(name = "newstorename") String newStoreName, @RequestParam(name = "username") String username){
        Optional<Retailer> foundRet = retRepo.findById(username);
        if(!foundRet.isPresent()){
            return new ResponseEntity<>("Error: username not recognized.", HttpStatus.BAD_REQUEST);
        }
        if(newStoreName.equals("")){
            return new ResponseEntity<>("Error: Store name is empty", HttpStatus.BAD_REQUEST);
        }
        Retailer exists = retRepo.getById(username);
        exists.setStorename(newStoreName);
        retRepo.save(exists);
        return new ResponseEntity<>("Store name updated", HttpStatus.OK);
    }

    /**
     * @param newFirstName
     * @param username
     * @return the updated entity or error message
     */
    @PutMapping("/firstname")
    public ResponseEntity<?> newFirstName(@RequestParam(name = "firstname") String newFirstName, @RequestParam(name = "username") String username){
        Optional<Retailer> foundRet = retRepo.findById(username);
        if(!foundRet.isPresent()){
            return new ResponseEntity<>("Error: Username not recognized", HttpStatus.BAD_REQUEST);
        }
        if(newFirstName.equals("") || newFirstName == null){
            return new ResponseEntity<>("Error: First name is empty or null", HttpStatus.BAD_REQUEST);
        }
        Retailer exists = retRepo.getById(username);
        exists.setFirstName(newFirstName);
        retRepo.save(exists);
        return new ResponseEntity<>("First name updated", HttpStatus.OK);
    }

    /**
     * @param newLastName
     * @param username
     * @return the updated entity, or error message
     */
    @PutMapping("/lastname")
    public ResponseEntity<?> newLastName(@RequestParam(name = "lastname") String newLastName, @RequestParam(name = "username") String username){
        Optional<Retailer> foundRet = retRepo.findById(username);
        if(!foundRet.isPresent()){
            return new ResponseEntity<>("Error: Username not recognized", HttpStatus.BAD_REQUEST);
        }
        if(newLastName.equals("")){
            return new ResponseEntity<>("Error: Last name is empty", HttpStatus.BAD_REQUEST);
        }
        Retailer exists = retRepo.getById(username);
        exists.setLastName(newLastName);
        retRepo.save(exists);
        return new ResponseEntity<>("Last name updated", HttpStatus.OK);
    }



}
