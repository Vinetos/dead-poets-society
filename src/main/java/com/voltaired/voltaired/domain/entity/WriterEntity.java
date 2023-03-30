package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.Seq;

public class WriterEntity {
    long id;
    String title;
    String name;
    String panName;

    Seq<LetterEntity> letters;
    Seq<CircleEntity> circles;

}
