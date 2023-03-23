package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity @Table(name = "writer")
@With @AllArgsConstructor @NoArgsConstructor
public class WriterModel extends PanacheEntity {
    @NotBlank String title;
    @NotBlank String name;
    @NotBlank String panName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "writer") List<LetterModel> letters;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "writer") List<CircleModel> circles;

}
