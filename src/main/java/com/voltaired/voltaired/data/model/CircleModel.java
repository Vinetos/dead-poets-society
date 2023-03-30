package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity @Table(name = "circle")
@With
@AllArgsConstructor @NoArgsConstructor
public class CircleModel extends PanacheEntity {
    public @NotBlank String name;

    public @ManyToMany(cascade = CascadeType.ALL) @JoinTable(
            name = "circle_letters",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "letter_id")
    ) List<LetterModel> letters;
    public @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "circle_writers",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "circle_id"))
    List<WriterModel> writers;
}
