package com.ringpro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ringpro.models.Matches;
import com.ringpro.repositories.MatchesRepository;

@Service
public class MatchesService {
    
    @Autowired
    MatchesRepository matchesRepository;

    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }
}
