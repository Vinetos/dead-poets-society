package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.Seq;

public class CircleEntity {
    long id;
    String name;

    Seq<LetterEntity> letters;
    WriterEntity writer;
}
