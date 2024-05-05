package com.ringpro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ringpro.models.Wrestlers;
import com.ringpro.services.WrestlersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// This is the rest API controller for interacting with WrestlersService

@RestController
public class WrestlersController {
       
    // this creates an instance of the WrestlersService class that will connect to the SQL DB
    // and is then used by the controller
    // @Autowired does something super cool???? 

// MOVE RESPONSEENTITY WORK INTO HERE, REMOVE FROM SERVICE


    @Autowired 
    WrestlersService wrestlersService;

    @GetMapping("/get-all-wrestlers")
    public ResponseEntity<List<Wrestlers>> allWrestlers() {
        List<Wrestlers> wrestlerList = wrestlersService.getAllWrestlers();      
        return new ResponseEntity<List<Wrestlers>>(wrestlerList, HttpStatus.OK);
    }

    @GetMapping("/get-wrestler/{id}")
    public ResponseEntity<Wrestlers> getSingleWrestlers(@PathVariable("id") Integer id){       
        return new ResponseEntity<Wrestlers>(wrestlersService.getWrestler(id), HttpStatus.OK);
    }

    @PostMapping("/add-wrestler")
    public ResponseEntity<Wrestlers> add(@RequestBody java.util.Map<String, String> body){
        return new ResponseEntity<Wrestlers>(wrestlersService.addWrestler(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-wrestler/{id}")
    public ResponseEntity<HttpStatus> removeWrestler(@PathVariable("id") Integer id) {
        try {
            wrestlersService.removeWrestler(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-wrestler/{id}")
    public ResponseEntity<Wrestlers> updateWrestlers(@PathVariable("id") Integer id, 
                                     @RequestBody java.util.Map<String, String> body){
                                        
        return new ResponseEntity<Wrestlers>(wrestlersService.updateWrestlers(id, body), HttpStatus.OK);
    }
}
