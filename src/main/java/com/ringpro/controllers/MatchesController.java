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
/*
    @GetMapping("/get-match/{id}")
    public Matches getSingleMatch(@PathVariable("id") Integer id){
        return matchesService.findById(id).get();
    }

    @DeleteMapping("/remove-match/{id}")
    public boolean deleteRow(@PathVariable("id") Integer id){
        if(!matchesService.findById(id).equals(Optional.empty())){
            matchesService.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update-match/{id}")
    public Matches updateMatches(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, String> body){
        Matches current = matchesService.findById(id).get();
        current.setWrestlerID_1(Integer.parseInt(body.get("wrestlerID_1")));
        current.setWrestlerID_2(Integer.parseInt(body.get("wrestlerID_2")));
        current.setMatchtype(body.get("matchtype"));
        current.setTimelimit(body.get("timelimit"));
        current.setFallrule(body.get("fallrule"));
        matchesService.save(current);
        return current;
    
    }

    @PostMapping("/add-match")
    public Matches create(@RequestBody Map<String, String> body){

        Integer wrestlerID_1 = Integer.parseInt(body.get("wrestlerID_1"));
        Integer wrestlerID_2 = Integer.parseInt(body.get("wrestlerID_2"));
        String matchtype = body.get("matchtype");
        String timelimit = body.get("timelimit");
        String fallrule = body.get("fallrule");

        Matches newMatch = new Matches(wrestlerID_1, wrestlerID_2, matchtype, timelimit, fallrule);
        return matchesService.save(newMatch);

    }
*/
}
