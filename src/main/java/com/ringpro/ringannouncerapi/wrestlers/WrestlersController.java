package com.ringpro.ringannouncerapi.wrestlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This is the rest API controller for interacting with Wrestlers

@RestController
public class WrestlersController {
       
    // this creates an instance of the WrestlersService class that will connect to the SQL DB
    // and is then used by the controller
    // @Autowired does something super cool???? 
    @Autowired 
    WrestlersService wrestlersService;

    @GetMapping("/get-all-wrestlers")
    public List<Wrestlers> getAllWrestlers(){
        return wrestlersService.findAll();
    }

    @GetMapping("/get-wrestler/{id}")
    public Wrestlers getSingleWrestlers(@PathVariable("id") Integer id){
            return wrestlersService.findById(id).get();
    }

    @DeleteMapping("/remove-wrestler/{id}")
    public boolean deleteRow(@PathVariable("id") Integer id){
        if(!wrestlersService.findById(id).equals(Optional.empty())){
            wrestlersService.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update-wrestler/{id}")
    public Wrestlers updateWrestlers(@PathVariable("id") Integer id, 
                                     @RequestBody java.util.Map<String, String> body){

        Wrestlers current = wrestlersService.findById(id).get();
        current.setName(body.get("name"));
        current.setMoniker(body.get("moniker"));
        current.setLocation(body.get("location"));
        current.setWeight(body.get("weight"));
        current.setNotes(body.get("notes"));
        wrestlersService.save(current);
        return current;
    }

    @PostMapping("/add-wrestler")
    public Wrestlers create(@RequestBody java.util.Map<String, String> body){

        String name = body.get("name");
        String moniker = body.get("monkiker");
        String location = body.get("location");
        String weight = body.get("weight");
        String notes = body.get("notes");
        Wrestlers newWrestler = new Wrestlers(name, moniker, location, weight, notes);

        return wrestlersService.save(newWrestler);

    }
}
