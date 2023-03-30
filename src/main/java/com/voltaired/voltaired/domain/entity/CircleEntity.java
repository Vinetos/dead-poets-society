package com.voltaired.voltaired.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @With
@Data
public class CircleEntity {
    public long id;
    public String name;
    public List<LetterEntity> letters;
    public WriterEntity writer;
}
