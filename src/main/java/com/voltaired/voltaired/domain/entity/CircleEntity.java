package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.Seq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With @Data
@AllArgsConstructor @NoArgsConstructor
public class CircleEntity {
    long id;
    String name;
    Seq<LetterEntity> letters;
    WriterEntity writer;
}
