package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity @Table(name = "letter")
@With @AllArgsConstructor @NoArgsConstructor
public class LetterModel extends PanacheEntity {

    public @NotNull ZonedDateTime date;
    public @NotBlank String subject;
    public @NotBlank String content;
    public @NotNull @ManyToOne CircleModel circle;
    public @ManyToOne WriterModel writer;

}
