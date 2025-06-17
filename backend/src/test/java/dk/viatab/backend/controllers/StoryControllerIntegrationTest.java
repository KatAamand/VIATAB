package dk.viatab.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllStories_returns200() throws Exception {
        mockMvc.perform(get("/api/stories"))
                .andExpect(status().isOk());
    }
}
