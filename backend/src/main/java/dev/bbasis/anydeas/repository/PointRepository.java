package dev.bbasis.anydeas.repository;

import dev.bbasis.anydeas.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
