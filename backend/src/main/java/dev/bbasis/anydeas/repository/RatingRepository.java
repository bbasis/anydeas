package dev.bbasis.anydeas.repository;

import dev.bbasis.anydeas.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
