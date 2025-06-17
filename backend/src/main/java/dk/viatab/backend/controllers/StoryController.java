package dk.viatab.backend.controllers;

import dk.viatab.backend.entities.Story;
import dk.viatab.backend.services.StoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin
public class StoryController {

    private final StoryService service;

    public StoryController(StoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Story> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Story create(@RequestBody Story story) {
        return service.create(story);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
