package dk.viatab.backend.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    @Test
    void testSettersAndGetters() {
        Story story = new Story();
        story.setId(1L);
        story.setTitle("Test title");
        story.setContent("Test content");

        assertEquals(1L, story.getId());
        assertEquals("Test title", story.getTitle());
        assertEquals("Test content", story.getContent());
    }
}
