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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "writer") @With @AllArgsConstructor @NoArgsConstructor
public class WriterModel extends PanacheEntity {
    public @NotBlank String title;
    public @NotBlank String name;
    public @NotBlank String penName;

    public @OneToMany(cascade = CascadeType.ALL, mappedBy = "writer")
    List<LetterModel> letters = new ArrayList<>();
    public @ManyToMany @JoinTable(
            name = "circle_writers",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "circle_id"))
    List<CircleModel> circles = new ArrayList<>();

}
