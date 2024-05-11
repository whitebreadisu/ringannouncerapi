package com.ringpro.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ringpro.models.Wrestler;
import com.ringpro.services.WrestlerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// This is the rest API controller for interacting with wrestlerService

@RestController
public class WrestlerController {
       
    // this creates an instance of the wrestlerService class that will connect to the SQL DB
    // and is then used by the controller
    // @Autowired does something super cool???? 

// MOVE RESPONSEENTITY WORK INTO HERE, REMOVE FROM SERVICE


    @Autowired 
    WrestlerService wrestlerService;

    @GetMapping("/get-all-wrestler")
    public ResponseEntity<List<Wrestler>> allwrestler() {
        List<Wrestler> wrestlerList = wrestlerService.getAllwrestler();      
        return new ResponseEntity<List<Wrestler>>(wrestlerList, HttpStatus.OK);
    }

    @GetMapping("/get-wrestler/{id}")
    public ResponseEntity<Wrestler> getSinglewrestler(@PathVariable("id") Integer id){       
        return new ResponseEntity<Wrestler>(wrestlerService.getWrestler(id), HttpStatus.OK);
    }

    @PostMapping("/add-wrestler")
    public ResponseEntity<Wrestler> add(@RequestBody java.util.Map<String, String> body){
        return new ResponseEntity<Wrestler>(wrestlerService.addWrestler(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-wrestler/{id}")
    public ResponseEntity<HttpStatus> removeWrestler(@PathVariable("id") Integer id) {
        try {
            wrestlerService.removeWrestler(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-wrestler/{id}")
    public ResponseEntity<Wrestler> updatewrestler(@PathVariable("id") Integer id, 
                                     @RequestBody Map<String, String> body){
                                        
        return new ResponseEntity<Wrestler>(wrestlerService.updatewrestler(id, body), HttpStatus.OK);
    }
}
