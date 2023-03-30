package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.data.repository.LetterRepository;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

@ApplicationScoped public class CircleConverter implements Converter<CircleModel, CircleEntity> {

    @Inject LetterRepository letterRepository;

    @Override public CircleEntity convertNotNull(CircleModel input) {
        return new CircleEntity()
                .withId(input.id)
                .withName(input.name)
                .withWriter(null)
                .withLetters(new ArrayList<>());
    }
}
