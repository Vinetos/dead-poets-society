package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity @Table(name = "circle")
@With @AllArgsConstructor @NoArgsConstructor
public class CircleModel extends PanacheEntity {
    @NotBlank String name;

    @OneToMany List<LetterModel> letters;
    @ManyToOne(cascade = CascadeType.ALL) WriterModel writer;
}
