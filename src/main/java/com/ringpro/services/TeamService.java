package com.ringpro.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ringpro.models.Team;
import com.ringpro.repositories.TeamRepository;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> geatAllTeams() {
        return teamRepository.findAll();
    }
    
    public Team getTeam(@RequestParam(required = true) Integer id){ 
        return teamRepository.findById(id).get();
    }

    public void removeTeam(@RequestParam(required = true) Integer id) {
        teamRepository.deleteById(id);
    }

    public Team addTeam(@RequestBody Map<String, String> body){

        Integer id = Integer.parseInt(body.get("id"));
        Integer wrestlerID = Integer.parseInt(body.get("wrestlerID"));
        Integer matchID = Integer.parseInt(body.get("matchID"));
        Integer team = Integer.parseInt(body.get("team"));

        Team newTeam = new Team(id, wrestlerID, matchID, team);
        return newTeam;
    }

    public Team updateTeam(@PathVariable("id") Integer id, @RequestBody Map<String, Integer> body) {
        Team teamToUpdate = teamRepository.findById(id).get();
//        teamToUpdate.setWrestlerID(body.get("wrestlerID"));
//        teamToUpdate.setMatchID(body.get("matchID"));
        teamToUpdate.setTeam(body.get("team"));
        teamRepository.save(teamToUpdate);
        return teamToUpdate;
    }
}
