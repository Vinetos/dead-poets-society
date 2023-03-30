package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped public class CircleConverter implements Converter<CircleModel, CircleEntity> {

    @Inject WriterConverter writerConverter;
    @Inject LetterConverter letterConverter;

    @Override public CircleEntity convertNotNull(CircleModel input) {
        return new CircleEntity().withId(input.id)
                                 .withName(input.name)
                                 .withWriters(() -> input.writers.stream()
                                                                 .map(writerConverter::convertNotNull)
                                                                 .toList())
                                 .withLetters(() -> input.letters.stream()
                                                                 .map(letterConverter::convertNotNull)
                                                                 .toList());
    }

}
