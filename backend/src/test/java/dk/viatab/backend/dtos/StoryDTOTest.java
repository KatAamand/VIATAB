package dk.viatab.backend.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryDTOTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        StoryDTO dto = new StoryDTO();
        dto.setId(42L);
        dto.setTitle("Test Title");
        dto.setContent("Test Content");

        assertEquals(42L, dto.getId());
        assertEquals("Test Title", dto.getTitle());
        assertEquals("Test Content", dto.getContent());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        StoryDTO dto = new StoryDTO(99L, "Another Title", "Another Content");

        assertEquals(99L, dto.getId());
        assertEquals("Another Title", dto.getTitle());
        assertEquals("Another Content", dto.getContent());
    }
}
