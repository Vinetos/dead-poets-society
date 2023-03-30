package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.TransactionalSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @With @Data
public class LetterEntity {
    public long id;
    public ZonedDateTime date;
    public String subject;
    public String content;
    public TransactionalSupplier<List<CircleEntity>> circle;
    public WriterEntity writer;
}
