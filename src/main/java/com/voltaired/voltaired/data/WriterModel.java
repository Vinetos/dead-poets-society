package com.voltaired.voltaired.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "poulpy")
@With @AllArgsConstructor @NoArgsConstructor
public class WriterModel extends PanacheEntity {
    @NotBlank String title;
    @NotBlank String name;
    @NotBlank String panName;

}
