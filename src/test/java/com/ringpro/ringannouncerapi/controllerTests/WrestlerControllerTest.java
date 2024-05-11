package com.ringpro.ringannouncerapi.controllerTests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringpro.controllers.WrestlerController;
import com.ringpro.models.Wrestler;
import com.ringpro.services.WrestlerService;

@WebMvcTest(WrestlerController.class)
public class WrestlerControllerTest {
    @MockBean
    private WrestlerService wrestlerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfwrestler() throws Exception {
        List<Wrestler> wrestlerList = new ArrayList<>(
            Arrays.asList(
                new Wrestler(1, "Test Wrestler 1", "Test Moniker 1", "Test Location 1", "1 lbs test", "test notes for wrestler 1"),
                new Wrestler(2, "Test Wrestler 2", "Test Moniker 2", "Test Location 2", "2 lbs test", "test notes for wrestler 2"),
                new Wrestler(3, "Test Wrestler 3", "Test Moniker 3", "Test Location 3", "3 lbs test", "test notes for wrestler 3"))
        );

        List<Wrestler> wrestlerServiceResponse = wrestlerList;

        when(wrestlerService.getAllwrestler()).thenReturn(wrestlerServiceResponse);
        mockMvc.perform(get("/get-all-wrestler"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(wrestlerList.size()))
            .andExpect(jsonPath("$[1].name", is("Test Wrestler 2")))
            .andDo(print());
    }

    @Test
    void shouldReturnSingleWrestler() throws Exception {
        int id = 1;
        Wrestler wrestler = new Wrestler(id, "GET Wrestler 1", "GET Moniker 1", "GET Location 1", "1 lbs GET", "GET notes for wrestler 1");

        when(wrestlerService.getWrestler(id)).thenReturn(wrestler);
        mockMvc.perform(get("/get-wrestler/{id}",id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(wrestler.getName()))
            .andExpect(jsonPath("$.moniker").value(wrestler.getMoniker()))
            .andExpect(jsonPath("$.location").value(wrestler.getLocation()))
            .andExpect(jsonPath("$.weight").value(wrestler.getWeight()))
            .andExpect(jsonPath("$.notes").value(wrestler.getNotes()))
            .andDo(print());
    }

    @Test
    void shouldCreateWrestler() throws Exception {
        Wrestler wrestler = new Wrestler("POST Wrestler 1", "POST Moniker 1", "POST Location 1", "1 lbs POST", "POST notes for wrestler 1");

        mockMvc.perform(post("/add-wrestler").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(wrestler)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldRemoveWrestler() throws Exception {
        int id = 0;
        doNothing().when(wrestlerService).removeWrestler(id);
        mockMvc.perform(delete("/remove-wrestler/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

/* 
    @Test
    void shouldUpdateWrestler() throws Exception {
        Integer id = 1;
        wrestler wrestler = new wrestler(id,"Test Wrestler 1", "Test Moniker 1", "Test Location 1", "1 lbs test", "test notes for wrestler 1");
        wrestler updatedwrestler = new wrestler(id, "updated", "updated", "updated", "updated","updated");
      
        
        when(wrestlerService.updatewrestler(id, body).thenReturn(wrestler));
        
        mockMvc.perform(put("/update-wrestler/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedwrestler)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(wrestler.getName()))
            .andExpect(jsonPath("$.moniker").value(wrestler.getMoniker()))
            .andExpect(jsonPath("$.location").value(wrestler.getLocation()))
            .andExpect(jsonPath("$.weight").value(wrestler.getWeight()))
            .andExpect(jsonPath("$.notes").value(wrestler.getNotes()))
            .andDo(print());
    }
*/
 
}
