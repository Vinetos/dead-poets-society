package com.voltaired.voltaired.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "letter")
@With @AllArgsConstructor @NoArgsConstructor
public class LetterModel extends PanacheEntity {
}
