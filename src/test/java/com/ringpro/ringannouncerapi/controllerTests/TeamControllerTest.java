package com.ringpro.ringannouncerapi.controllerTests;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ringpro.controllers.TeamController;
import com.ringpro.services.TeamService;
import com.ringpro.models.Team;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @MockBean
    private TeamService teamService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnListOfTeams() throws Exception{
        List<Team> teamList = new ArrayList<>(
            Arrays.asList(
                new Team(1,1,1),
                new Team(2,1,2),
                new Team(3,2,1),
                new Team(4,2,2))
        );
        
        List<Team> teamServiceResponse = teamList;

        when(teamService.geatAllTeams()).thenReturn(teamServiceResponse);
        mockMvc.perform(get("/get-all-teams"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(teamList.size()))
            .andExpect(jsonPath("$[1].team", is(2)))
            .andDo(print());
    }
    
    @Test
    void shouldReturnSingleTeam() throws Exception {
        Integer id = 1;
        Team team = new Team(id,2,3,4);

        when(teamService.getTeam(id)).thenReturn(team);
        mockMvc.perform(get("/get-team/{id}", id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
//            .andExpect(jsonPath("$.wrestlerID").value(team.getWrestlerID()))
//            .andExpect(jsonPath("$.matchID").value(team.getMatchID()))
            .andExpect(jsonPath("$.team").value(team.getTeam()))
            .andDo(print());  
    }

    @Test
    void shouldCreateTeam() throws Exception {
        Team team = new Team (1,2,3);
        mockMvc.perform(post("/add-team").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(team)))
            .andExpect(status().isCreated())
            .andDo(print());
    }
    
    
    @Test
    void shouldRemoveTeam() throws Exception {
        Integer id = 0;
        doNothing().when(teamService).removeTeam(id);
        mockMvc.perform(delete("/remove-team/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }


}
