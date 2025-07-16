package com.relationship.center.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CenterApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveAdicionarAtendenteComSucesso() throws Exception {
        String json = """
                    {
                      "team": "Cartões",
                      "name": "Fabio"
                    }
                """;

        mockMvc.perform(post("/api/attendant")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Criado com sucesso"));
    }

    @Test
    void deveAdicionarProblemaComSucesso() throws Exception {
        String json = """
                {
                  "team": "Cartões",
                  "message": "Problema no sistema"
                }
                """;

        mockMvc.perform(post("/api/problem")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Adicionado à fila"));

    }
}
