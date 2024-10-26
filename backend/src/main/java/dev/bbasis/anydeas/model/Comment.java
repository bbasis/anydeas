package dev.bbasis.anydeas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends Model {

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate added;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
