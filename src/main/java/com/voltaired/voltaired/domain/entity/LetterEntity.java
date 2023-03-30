package com.voltaired.voltaired.domain.entity;

import java.time.ZonedDateTime;

public class LetterEntity {

    long id;
    ZonedDateTime date;
    String subject;
    String content;
    CircleEntity circle;
    WriterEntity writer;
}
