package com.voltaired.voltaired.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.ZonedDateTime;

@AllArgsConstructor @NoArgsConstructor @With @Data
public class LetterEntity {
    public long id;
    public ZonedDateTime date;
    public String subject;
    public String content;
    public CircleEntity circle;
    public WriterEntity writer;
}
