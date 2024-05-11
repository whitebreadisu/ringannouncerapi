package com.ringpro.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ringpro.models.Match;
import com.ringpro.repositories.MatchRepository;

@Service
public class MatchService {
    
    @Autowired
    MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatches(@RequestParam(required = true) Integer id) {
        return matchRepository.findById(id).get();
    }

    public void removeMatch(@RequestParam(required = true) Integer id) {
        matchRepository.deleteById(id);
    }

    public Match addMatches(@RequestBody Map<String, String> body){
        
        Integer id = Integer.parseInt(body.get("id"));
        String matchtype = body.get("matchtype");
        String timelimit = body.get("timelimit");
        String fallrule = body.get("fallrule");

        Match newMatch = new Match(id, matchtype, timelimit, fallrule);
        return newMatch;
    }    

    public  Match updateMatches(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, String> body) {
        Match matchToUpdate = matchRepository.findById(id).get();
        matchToUpdate.setMatchtype(body.get("matchtype"));
        matchToUpdate.setTimelimit(body.get("timelimit"));
        matchToUpdate.setFallrule(body.get("fallrule"));
        matchRepository.save(matchToUpdate);
        return matchToUpdate;

    }
}
