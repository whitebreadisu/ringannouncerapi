package com.ringpro.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ringpro.models.Match;
import com.ringpro.services.MatchService;

@RestController
public class MatchController {
    
    @Autowired
    MatchService matchService;

    @GetMapping("/get-all-matches")
    public ResponseEntity<List<Match>> getAllMatches(){
        List<Match> matchesList = matchService.getAllMatches();
        return new ResponseEntity<List<Match>>(matchesList, HttpStatus.OK);
    }
 
    @GetMapping("/get-match/{id}")
    public ResponseEntity<Match> getSingleMatch(@PathVariable("id") Integer id){
        return new ResponseEntity<>(matchService.getMatch(id), HttpStatus.OK);
    }

    @PostMapping("/add-match")
    public ResponseEntity<Match> addMatch(@RequestBody Map<String, String> body){
        return new ResponseEntity<Match>(matchService.addMatch(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-match/{id}")
    public ResponseEntity<HttpStatus> removeMatch(@PathVariable("id") Integer id) {
        try {
            matchService.removeMatch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-match/{id}")
    public ResponseEntity<Match> updateMatches(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, String> body){
        return new ResponseEntity<Match>(matchService.updateMatch(id, body), HttpStatus.OK);
    }
}
