package com.ringpro.ringannouncerapi.wrestlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        List<Wrestlers> wrestlers = new ArrayList<>(
            Arrays.asList(
                new Wrestlers(1, "Test Wrestler 1", "Test Moniker 1", "Test Location 1", "1 lbs test", "test notes for wrestler 1"),
                new Wrestlers(2, "Test Wrestler 2", "Test Moniker 2", "Test Location 2", "2 lbs test", "test notes for wrestler 2"),
                new Wrestlers(3, "Test Wrestler 3", "Test Moniker 3", "Test Location 3", "3 lbs test", "test notes for wrestler 3"))
        );

        when(wrestlersService.findAll()).thenReturn(wrestlers);
        mockMvc.perform(get("/get-all-wrestlers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(wrestlers.size()))
            .andExpect(jsonPath("$[1].name", is("Test Wrestler 2")))
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
    void shouldReturnWrestler() throws Exception {
        int id = 1;
        Wrestlers wrestlers = new Wrestlers(id, "GET Wrestler 1", "GET Moniker 1", "GET Location 1", "1 lbs GET", "GET notes for wrestler 1");

        when(wrestlersService.findById(id)).thenReturn(Optional.of(wrestlers));
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

}
