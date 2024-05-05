package com.ringpro.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ringpro.models.Matches;

public interface MatchesService extends JpaRepository<Matches, Integer>{
    
}
