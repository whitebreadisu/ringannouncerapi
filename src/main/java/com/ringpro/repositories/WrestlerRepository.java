package com.ringpro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ringpro.models.Wrestler; 

// this class is used for connecting the sql DB to interact with an instance of the Wrestlers model class
// the 'extends' statement informs the class of its databaseness, allowing us to interact
// with an instance of this class to do database things like create/modify/delete wrestler records

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Integer>{
    
}
