package com.ringpro.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ringpro.models.Matches;
import com.ringpro.repositories.MatchesRepository;

@Service
public class MatchesService {
    
    @Autowired
    MatchesRepository matchesRepository;

    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }

    public Matches getMatches(@RequestParam(required = true) Integer id) {
        return matchesRepository.findById(id).get();
    }

    public void removeMatch(@RequestParam(required = true) Integer id) {
        matchesRepository.deleteById(id);
    }

    public Matches createMatches(@RequestBody Map<String, String> body){
        
        Integer id = Integer.parseInt(body.get("id"));
        Integer wrestlerID_1 = Integer.parseInt(body.get("wrestlerID_1"));
        Integer wrestlerID_2 = Integer.parseInt(body.get("wrestlerID_2"));
        String matchtype = body.get("matchtype");
        String timelimit = body.get("timelimit");
        String fallrule = body.get("fallrule");

        Matches newMatch = new Matches(id, wrestlerID_1, wrestlerID_2, matchtype, timelimit, fallrule);
        return newMatch;
    }    

    public  Matches updateMatches(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, String> body) {
        Matches matchToUpdate = matchesRepository.findById(id).get();
        matchToUpdate.setWrestlerID_1(Integer.parseInt(body.get("wrestlerID_1")));
        matchToUpdate.setWrestlerID_2(Integer.parseInt(body.get("wrestlerID_2")));
        matchToUpdate.setMatchtype(body.get("matchtype"));
        matchToUpdate.setTimelimit(body.get("timelimit"));
        matchToUpdate.setFallrule(body.get("fallrule"));
        matchesRepository.save(matchToUpdate);
        return matchToUpdate;

    }
}
