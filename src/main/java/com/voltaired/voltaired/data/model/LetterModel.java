package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity @Table(name = "letter")
@With @AllArgsConstructor @NoArgsConstructor
public class LetterModel extends PanacheEntity {

    @NotNull ZonedDateTime date;
    @NotBlank String subject;
    @NotBlank String content;
    @NotNull @ManyToOne @JoinColumn(name = "circle") CircleModel circle;

}
