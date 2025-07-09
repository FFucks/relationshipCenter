package com.relationship.center.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CenterApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveAdicionarUmAtendente() throws Exception {
        String json = """
            {
              "name": "Equipe 1",
              "attendants": [{"name": "João"}]
            }
        """;

        /*mockMvc.perform(post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()); // ou .isCreated() dependendo do retorno*/
    }
}
