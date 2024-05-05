package com.ringpro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ringpro.models.Matches;

public interface MatchesRepository extends JpaRepository<Matches, Integer>{
    
}
