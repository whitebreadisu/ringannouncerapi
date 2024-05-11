package com.ringpro.ringannouncerapi.controllerTests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import com.ringpro.controllers.MatchController;
import com.ringpro.models.Match;
import com.ringpro.services.MatchService;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {
    @MockBean
    private MatchService matchesService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfMatches() throws Exception {
        List<Match> matchesList = new ArrayList<>(
            Arrays.asList (
                new Match("type1", "timelimit1", "fallrule1"),
                new Match("type2", "timelimit2", "fallrule2"),
                new Match("type3", "timelimit3", "fallrule3"))
        );

        List<Match> matchesServiceResponse = matchesList;

        when(matchesService.getAllMatches()).thenReturn(matchesServiceResponse);
        mockMvc.perform(get("/get-all-matches"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(matchesList.size()))
            .andExpect(jsonPath("$[1].matchtype", is("type2")))
            .andDo(print());
    }

    @Test
    void shouldReturnSingleMatch() throws Exception {
        Integer id = 1;
        Match matches = new Match(id,"type1", "timelimit1", "fallrule1");

        when(matchesService.getMatches(id)).thenReturn(matches);
        mockMvc.perform(get("/get-match/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.matchtype").value(matches.getMatchtype()))
            .andExpect(jsonPath("$.fallrule").value(matches.getFallrule()))
            .andExpect(jsonPath("$.timelimit").value(matches.getTimelimit()))
            .andDo(print());

    }

    @Test
    void shouldCreateMatch() throws Exception {
        Match matches = new Match("POST type1", "POST timelimit1", "POST fallrule1");

        mockMvc.perform(post("/add-match").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(matches)))
        .andExpect(status().isCreated())
        .andDo(print());
    }

    @Test
    void shouldRemoveMatch() throws Exception {
        int id = 0;
        doNothing().when(matchesService).removeMatch(id);
        mockMvc.perform(delete("/remove-match/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }

 /*
    @Test
    void shouldUpdateMatches() throws Exception {
        Integer id = 1;
        Matches matches = new Matches(1,1, 2, "type1", "timelimit1", "fallrule1");
        Matches updatedMatches = new Matches(1,3, 4, "updated", "updated", "updated");
        
        when(matchesService.updateMatches(id, body).thenReturn(matches));

        mockMvc.perform(put("/update-match/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedMatches)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.wrestlerID_1").value(matches.getWrestlerID_1()))
            .andExpect(jsonPath("$.wrestlerID_2").value(matches.getWrestlerID_2()))
            .andExpect(jsonPath("$.matchtype").value(matches.getMatchtype()))
            .andExpect(jsonPath("$.fallrule").value(matches.getFallrule()))
            .andExpect(jsonPath("$.timelimit").value(matches.getTimelimit()))
            .andDo(print());
    
    }
 */   
}
