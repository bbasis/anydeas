package dev.bbasis.anydeas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "points")
public class Point extends Model {

    @Basic(optional = false)
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;
}
