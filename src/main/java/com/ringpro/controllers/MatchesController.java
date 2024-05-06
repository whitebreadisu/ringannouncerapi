package com.ringpro.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ringpro.models.Matches;
import com.ringpro.repositories.MatchesRepository;
import com.ringpro.services.MatchesService;

@RestController
public class MatchesController {
    
    @Autowired
    MatchesService matchesService;

    @GetMapping("/get-all-matches")
    public ResponseEntity<List<Matches>> getAllMatches(){
        List<Matches> matchesList = matchesService.getAllMatches();
        return new ResponseEntity<List<Matches>>(matchesList, HttpStatus.OK);
    }
 
    @GetMapping("/get-match/{id}")
    public ResponseEntity<Matches> getSingleMatch(@PathVariable("id") Integer id){
        return new ResponseEntity<>(matchesService.getMatches(id), HttpStatus.OK);
    }

    @DeleteMapping("/remove-match/{id}")
    public ResponseEntity<HttpStatus> removeMatch(@PathVariable("id") Integer id) {
        try {
            matchesService.removeMatch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-match/{id}")
    public ResponseEntity<Matches> updateMatches(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, String> body){
        return new ResponseEntity<Matches>(matchesService.updateMatches(id, body), HttpStatus.OK);
    
    }

    @PostMapping("/add-match")
    public ResponseEntity<Matches> addMatches(@RequestBody Map<String, String> body){
        return new ResponseEntity<Matches>(matchesService.createMatches(body), HttpStatus.CREATED);

    }
}
