package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.WriterModel;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped
public class WriterConverter implements Converter<WriterModel, WriterEntity> {

    @Inject CircleConverter circleConverter;
    @Inject LetterConverter letterConverter;

    @Override
    public WriterEntity convertNotNull(WriterModel input) {
        return new WriterEntity().withId(input.id)
                                 .withName(input.name)
                                 .withPenName(input.penName)
                                 .withTitle(input.title)
                                 .withCircles(input.circles.stream()
                                                           .map(circleConverter::convertWithoutResolve)
                                                           .toList())
                                 .withLetters(input.letters.stream()
                                                           .map(letterConverter::convertWithoutResolve)
                                                           .toList());
    }

    public WriterEntity convertWithoutResolve(WriterModel input) {
        return new WriterEntity().withId(input.id)
                                 .withName(input.name)
                                 .withPenName(input.penName)
                                 .withTitle(input.title)
                                 .withCircles(new ArrayList<>())
                                 .withLetters(new ArrayList<>());
    }
}
