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

import com.ringpro.models.Team;
import com.ringpro.services.TeamService;

@RestController
public class TeamController {
    
    @Autowired
    TeamService teamService;

    @GetMapping("/get-all-teams")
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> teamList = teamService.geatAllTeams();
        return new ResponseEntity<List<Team>>(teamList, HttpStatus.OK);
    }

    @GetMapping("/get-team/{id}")
    public ResponseEntity<Team> getSingleTeam(@PathVariable("id") Integer id){
        return new ResponseEntity<>(teamService.getTeam(id), HttpStatus.OK);
    }

    @PostMapping("/add-team")
    public ResponseEntity<Team> addTeam(@RequestBody Map<String, String> body){
        return new ResponseEntity<Team>(teamService.addTeam(body), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-team/{id}")
    public ResponseEntity<HttpStatus> removeteam(@PathVariable("id") Integer id) {
        try {
            teamService.removeTeam(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-team/{id}")
    public ResponseEntity<Team> updateteames(@PathVariable("id") Integer id, 
                                 @RequestBody Map<String, Integer> body){
        return new ResponseEntity<Team>(teamService.updateTeam(id, body), HttpStatus.OK);
    }
}
