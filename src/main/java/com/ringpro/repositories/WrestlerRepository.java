package com.ringpro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ringpro.models.Wrestler; 

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Integer>{
    
}
