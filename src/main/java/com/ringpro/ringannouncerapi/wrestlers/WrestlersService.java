package com.ringpro.ringannouncerapi.wrestlers;

import org.springframework.data.jpa.repository.JpaRepository; 

// this class is used for connecting the sql DB to interact with an instance of the Wrestlers model class
// the 'extends' statement informs the class of its databaseness, allowing us to interact
// with an instance of this class to do database things like create/modify/delete wrestler records

public interface WrestlersService extends JpaRepository<Wrestlers, Integer>{
    
}
