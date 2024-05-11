package com.ringpro.services;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ringpro.models.Wrestler;
import com.ringpro.repositories.WrestlerRepository;

@Service
public class WrestlerService {
    
    @Autowired
    WrestlerRepository wrestlerRepository;
    
    // Get ALL wrestler
    public List<Wrestler> getAllwrestler() 
    {
        return wrestlerRepository.findAll();
    
    }
    
    // Get single wrestler by id
     public Wrestler getWrestler(@RequestParam(required = true) Integer id) {
            return wrestlerRepository.findById(id).get();
    }

    // Add a single wrestler
    public Wrestler addWrestler(@RequestBody Map<String, String> body){

        String name = body.get("name");
        String moniker = body.get("moniker");
        String location = body.get("location");
        String weight = body.get("weight");
        String notes = body.get("notes");
        Wrestler newWrestler = new Wrestler(name, moniker, location, weight, notes);
        wrestlerRepository.save(newWrestler);
        return newWrestler;

    }

    // Delete a single wrestler by id
    // look at below comments.... where do I do error handling?
    // what about deleting a wrestler that is FK constraint to matches?
    //   Probably need to think about this as a 'deactivate' flag of some sort, or if its even needed
    public void removeWrestler(Integer id) {
              wrestlerRepository.deleteById(id);
    }

    public Wrestler updatewrestler(Integer id, @RequestBody Map<String, String> body) {

        Wrestler wrestlerToUpdate = wrestlerRepository.findById(id).get();
        wrestlerToUpdate.setName(body.get("name"));
        wrestlerToUpdate.setMoniker(body.get("moniker"));
        wrestlerToUpdate.setLocation(body.get("location"));
        wrestlerToUpdate.setWeight(body.get("weight"));
        wrestlerToUpdate.setNotes(body.get("notes"));
        wrestlerRepository.save(wrestlerToUpdate);
        return wrestlerToUpdate;
    }
}

