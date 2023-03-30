package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.LetterModel;
import com.voltaired.voltaired.data.repository.CircleRepository;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped public class LetterConverter implements Converter<LetterModel, LetterEntity> {

    @Inject CircleRepository circleRepository;
    @Inject CircleConverter circleConverter;

    @Inject WriterConverter writerConverter;

    @Transactional @Override public LetterEntity convertNotNull(LetterModel input) {
        return new LetterEntity(
                input.id,
                input.date,
                input.content,
                input.subject,
                () -> input.circles.stream().map(circleConverter::convertNotNull).toList(),
                writerConverter.convertNotNull(input.writer)
        );
    }
}
