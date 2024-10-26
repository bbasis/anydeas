package dev.bbasis.anydeas.repository;

import dev.bbasis.anydeas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
