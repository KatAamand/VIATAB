package dk.viatab.backend.repositories;

import dk.viatab.backend.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
