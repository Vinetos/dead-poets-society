package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.Seq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor @NoArgsConstructor @With
@Data
public class CircleEntity {
    public long id;
    public String name;
    public Seq<LetterEntity> letters;
    public WriterEntity writer;
}
