package dk.viatab.backend.services;

import dk.viatab.backend.entities.Story;
import dk.viatab.backend.repositories.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StoryServiceTest {

    private StoryRepository repo;
    private StoryService service;

    @BeforeEach
    void setUp() {
        repo = mock(StoryRepository.class);
        service = new StoryService(repo);
    }

    @Test
    void getAll_shouldReturnList() {
        when(repo.findAll()).thenReturn(List.of(new Story()));
        List<Story> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void create_shouldSaveStory() {
        Story story = new Story();
        when(repo.save(story)).thenReturn(story);
        Story result = service.create(story);
        assertEquals(story, result);
    }

    @Test
    void delete_shouldCallDeleteById() {
        service.delete(1L);
        verify(repo).deleteById(1L);
    }
}
