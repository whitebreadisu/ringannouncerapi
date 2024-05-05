package com.ringpro.services;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ringpro.models.Wrestlers;
import com.ringpro.repositories.WrestlersRepository;

@Service
public class WrestlersService {
    
    @Autowired
    WrestlersRepository wrestlersRepository;
    
    // Get ALL Wrestlers
    public List<Wrestlers> getAllWrestlers() 
    {
        return wrestlersRepository.findAll();
    }
    
    // Get single wrestler by id
     public Wrestlers getWrestler(@RequestParam(required = true) Integer id) {
            return wrestlersRepository.findById(id).get();
    }

    // Add a single wrestler
    public Wrestlers addWrestler(@RequestBody Map<String, String> body){

        String name = body.get("name");
        String moniker = body.get("moniker");
        String location = body.get("location");
        String weight = body.get("weight");
        String notes = body.get("notes");
        Wrestlers newWrestler = new Wrestlers(name, moniker, location, weight, notes);
        wrestlersRepository.save(newWrestler);
        return newWrestler;

    }

    // Delete a single wrestler by id
    // look at below comments.... where do I do error handling?
    // what about deleting a wrestler that is FK constraint to matches?
    //   Probably need to think about this as a 'deactivate' flag of some sort, or if its even needed
    public void removeWrestler(Integer id) {
              wrestlersRepository.deleteById(id);
    }

    public Wrestlers updateWrestlers(Integer id, @RequestBody Map<String, String> body) {

        Wrestlers wrestlerToUpdate = wrestlersRepository.findById(id).get();
        wrestlerToUpdate.setName(body.get("name"));
        wrestlerToUpdate.setMoniker(body.get("moniker"));
        wrestlerToUpdate.setLocation(body.get("location"));
        wrestlerToUpdate.setWeight(body.get("weight"));
        wrestlerToUpdate.setNotes(body.get("notes"));
        wrestlersRepository.save(wrestlerToUpdate);
        return wrestlerToUpdate;
    }
}

