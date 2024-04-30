package com.ringpro.ringannouncerapi.wrestlers;

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

@WebMvcTest(WrestlersController.class)
public class WrestlersControllerTest {
    @MockBean
    private WrestlersService wrestlersService;

    @Autowired
    private MockMvc mockMvc;

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






    


}
