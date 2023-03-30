package com.voltaired.voltaired.domain.entity;

import com.voltaired.voltaired.util.TransactionalSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @With
@Data
public class CircleEntity {
    public long id;
    public String name;
    public TransactionalSupplier<List<LetterEntity>> letters;
    public TransactionalSupplier<List<WriterEntity>> writers;
}
