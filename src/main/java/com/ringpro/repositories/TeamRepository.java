package com.ringpro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ringpro.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    
}
