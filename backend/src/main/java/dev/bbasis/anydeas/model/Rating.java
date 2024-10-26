package dev.bbasis.anydeas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating extends Model {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Basic(optional = false)
    @Column(nullable = false)
    private int ratingValue;

    @Basic(optional = true)
    @Column(nullable = true)
    private String text;
}
