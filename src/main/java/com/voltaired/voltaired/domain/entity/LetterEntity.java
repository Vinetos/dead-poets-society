package com.voltaired.voltaired.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.ZonedDateTime;

@With @Data
@AllArgsConstructor @NoArgsConstructor
public class LetterEntity {

    long id;
    ZonedDateTime date;
    String subject;
    String content;
    CircleEntity circle;
    WriterEntity writer;
}
