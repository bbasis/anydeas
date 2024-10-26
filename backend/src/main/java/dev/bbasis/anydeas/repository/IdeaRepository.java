package dev.bbasis.anydeas.repository;

import dev.bbasis.anydeas.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {
}
