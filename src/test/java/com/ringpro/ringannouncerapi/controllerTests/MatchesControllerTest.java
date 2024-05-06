package com.ringpro.ringannouncerapi.controllerTests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ringpro.controllers.MatchesController;
import com.ringpro.models.Matches;
import com.ringpro.services.MatchesService;

@WebMvcTest(MatchesController.class)
public class MatchesControllerTest {
    @MockBean
    private MatchesService matchesService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfMatches() throws Exception {
        List<Matches> matchesList = new ArrayList<>(
            Arrays.asList (
                new Matches(1, 2, "type1", "timelimit1", "fallrule1"),
                new Matches(5, 3, "type2", "timelimit2", "fallrule2"),
                new Matches(2, 4, "type3", "timelimit3", "fallrule3"))
        );

        List<Matches> matchesServiceResponse = matchesList;

        when(matchesService.getAllMatches()).thenReturn(matchesServiceResponse);
        mockMvc.perform(get("/get-all-matches"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(matchesList.size()))
            .andExpect(jsonPath("$[1].matchtype", is("type2")))
            .andDo(print());
    }
    
//****************************
// Matches constructor does not include id for match record...
// need to update Matches.java with constructor that includeds id...
// and then include the commented line below in the test
//****************************
    @Test
    void shouldReturnSingleMatch() throws Exception {
        Integer id = 1;
        Matches matches = new Matches(1, 2, "type1", "timelimit1", "fallrule1");

        when(matchesService.getMatches(id)).thenReturn(matches);
        mockMvc.perform(get("/get-match/{id}", id))
            .andExpect(status().isOk())
//          .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.wrestlerID_1").value(matches.getWrestlerID_1()))
            .andExpect(jsonPath("$.wrestlerID_2").value(matches.getWrestlerID_2()))
            .andExpect(jsonPath("$.matchtype").value(matches.getMatchtype()))
            .andExpect(jsonPath("$.fallrule").value(matches.getFallrule()))
            .andExpect(jsonPath("$.timelimit").value(matches.getTimelimit()))
            .andDo(print());

    }
}
