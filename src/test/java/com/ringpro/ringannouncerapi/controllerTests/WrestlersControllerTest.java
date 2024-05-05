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
import com.ringpro.controllers.WrestlersController;
import com.ringpro.models.Wrestlers;
import com.ringpro.services.WrestlersService;

@WebMvcTest(WrestlersController.class)
public class WrestlersControllerTest {
    @MockBean
    private WrestlersService wrestlersService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfWrestlers() throws Exception {
        List<Wrestlers> wrestlersList = new ArrayList<>(
            Arrays.asList(
                new Wrestlers(1, "Test Wrestler 1", "Test Moniker 1", "Test Location 1", "1 lbs test", "test notes for wrestler 1"),
                new Wrestlers(2, "Test Wrestler 2", "Test Moniker 2", "Test Location 2", "2 lbs test", "test notes for wrestler 2"),
                new Wrestlers(3, "Test Wrestler 3", "Test Moniker 3", "Test Location 3", "3 lbs test", "test notes for wrestler 3"))
        );

        List<Wrestlers> wrestlersServiceResponse = wrestlersList;

        when(wrestlersService.getAllWrestlers()).thenReturn(wrestlersServiceResponse);
        mockMvc.perform(get("/get-all-wrestlers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(wrestlersList.size()))
            .andExpect(jsonPath("$[1].name", is("Test Wrestler 2")))
            .andDo(print());
    }

    @Test
    void shouldReturnSingleWrestler() throws Exception {
        int id = 1;
        Wrestlers wrestlers = new Wrestlers(id, "GET Wrestler 1", "GET Moniker 1", "GET Location 1", "1 lbs GET", "GET notes for wrestler 1");

        when(wrestlersService.getWrestler(id)).thenReturn(wrestlers);
        mockMvc.perform(get("/get-wrestler/{id}",id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(wrestlers.getName()))
            .andExpect(jsonPath("$.moniker").value(wrestlers.getMoniker()))
            .andExpect(jsonPath("$.location").value(wrestlers.getLocation()))
            .andExpect(jsonPath("$.weight").value(wrestlers.getWeight()))
            .andExpect(jsonPath("$.notes").value(wrestlers.getNotes()))
            .andDo(print());
    }

    @Test
    void shouldCreateWrestler() throws Exception {
        Wrestlers wrestlers = new Wrestlers("POST Wrestler 1", "POST Moniker 1", "POST Location 1", "1 lbs POST", "POST notes for wrestler 1");

        mockMvc.perform(post("/add-wrestler").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(wrestlers)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldRemoveWrestler() throws Exception {
        int id = 0;
        doNothing().when(wrestlersService).removeWrestler(id);
        mockMvc.perform(delete("/remove-wrestler/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

/*
    @Test
    void shouldUpdateWrestler() throws Exception {
        Integer id = 1;
        Wrestlers wrestlers = new Wrestlers(id,"Test Wrestler 1", "Test Moniker 1", "Test Location 1", "1 lbs test", "test notes for wrestler 1");
        Wrestlers updatedWrestlers = new Wrestlers(id, "updated", "updated", "updated", "updated","updated");
      
        
        when(wrestlersService.updateWrestlers(id, wrestlers.toString()).thenReturn(wrestlers));
        
        mockMvc.perform(put("/update-wrestler/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedWrestlers)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(wrestlers.getName()))
            .andExpect(jsonPath("$.moniker").value(wrestlers.getMoniker()))
            .andExpect(jsonPath("$.location").value(wrestlers.getLocation()))
            .andExpect(jsonPath("$.weight").value(wrestlers.getWeight()))
            .andExpect(jsonPath("$.notes").value(wrestlers.getNotes()))
            .andDo(print());
    }
*/
 
}
