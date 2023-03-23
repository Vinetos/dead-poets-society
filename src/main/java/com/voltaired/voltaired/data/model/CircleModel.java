package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "circle")
@With @AllArgsConstructor @NoArgsConstructor
public class CircleModel extends PanacheEntity {
    @NotBlank String name;
}
