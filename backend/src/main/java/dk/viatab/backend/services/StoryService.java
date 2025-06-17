package dk.viatab.backend.services;

import dk.viatab.backend.entities.Story;
import dk.viatab.backend.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {
    private final StoryRepository repo;

    public StoryService(StoryRepository repo) {
        this.repo = repo;
    }

    public List<Story> getAll() {
        return repo.findAll();
    }

    public Story create(Story story) {
        return repo.save(story);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
