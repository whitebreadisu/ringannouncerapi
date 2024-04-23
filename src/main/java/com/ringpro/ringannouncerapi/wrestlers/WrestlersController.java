package com.ringpro.ringannouncerapi.wrestlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WrestlersController {
       
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

}

