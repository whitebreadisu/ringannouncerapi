package com.ringpro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ringpro.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{
    
}
