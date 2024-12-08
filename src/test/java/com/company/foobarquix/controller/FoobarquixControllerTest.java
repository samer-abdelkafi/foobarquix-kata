package com.company.foobarquix.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoobarquixController.class)
class FoobarquixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenValidNumber_whenProcessNumber_thenReturnsProcessedResult() throws Exception {
        mockMvc.perform(get("/api/foobarquix/15"))
                .andExpect(status().isOk())
                .andExpect(content().string("FOOBARBAR"));
    }

    @Test
    void givenInvalidNumber_whenProcessNumber_thenReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/foobarquix/101"))
                .andExpect(status().isBadRequest());
    }
}
