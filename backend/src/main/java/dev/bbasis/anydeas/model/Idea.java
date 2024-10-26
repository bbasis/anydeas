package dev.bbasis.anydeas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ideas")
public class Idea extends Model{

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    private double overallRating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdeaState state;

    @OneToMany(mappedBy = "idea", orphanRemoval = true)
    private List<Point> pros = new ArrayList<>();

    @OneToMany(mappedBy = "idea", orphanRemoval = true)
    private List<Point> cons = new ArrayList<>();
}
