package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.Seq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor @NoArgsConstructor @With @Data
public class WriterEntity {
    public long id;
    public String title;
    public String name;
    public String penName;

    public Seq<LetterEntity> letters;
    public Seq<CircleEntity> circles;

}
