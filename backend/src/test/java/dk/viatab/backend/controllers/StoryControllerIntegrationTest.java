package dk.viatab.backend.controllers;

import dk.viatab.backend.entities.Story;
import dk.viatab.backend.repositories.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StoryRepository storyRepository;

    @BeforeEach
    void setup() {
        storyRepository.deleteAll();
    }

    @Test
    void getAllStories_returns200() throws Exception {
        Story story = new Story();
        story.setTitle("Test title");
        story.setContent("Test content");
        storyRepository.save(story);

        mockMvc.perform(get("/api/stories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Test title"));
    }

    @Test
    void createStory_returns200AndPersists() throws Exception {
        String json = "{\"title\":\"New Story\",\"content\":\"Some content\"}";

        mockMvc.perform(post("/api/stories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Story"));
    }

    @Test
    void deleteStory_returns200() throws Exception {
        Story story = new Story();
        story.setTitle("To be deleted");
        story.setContent("Delete me");
        Story saved = storyRepository.save(story);

        mockMvc.perform(delete("/api/stories/" + saved.getId()))
                .andExpect(status().isOk());
    }
}