package com.voltaired.voltaired.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;
import java.util.function.Supplier;

@AllArgsConstructor
@NoArgsConstructor
@With
@Data
public class WriterEntity {
    public long id;
    public String title;
    public String name;
    public String penName;

    public Supplier<List<LetterEntity>> letters;
    public Supplier<List<CircleEntity>> circles;

}
