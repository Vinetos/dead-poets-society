package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.LetterModel;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped public class LetterConverter implements Converter<LetterModel, LetterEntity> {

    @Inject CircleConverter circleConverter;

    @Inject WriterConverter writerConverter;

    @Override public LetterEntity convertNotNull(LetterModel input) {
        return new LetterEntity(
                input.id,
                input.date,
                input.content,
                input.subject,
                input.circles.stream().map(circleConverter::convertWithoutResolve).toList(),
                writerConverter.convert(input.writer)
        );
    }

    public LetterEntity convertWithoutResolve(LetterModel input) {
        return new LetterEntity(
                input.id,
                input.date,
                input.content,
                input.subject,
                new ArrayList<>(),
                new WriterEntity().withId(input.id)
        );
    }
}
