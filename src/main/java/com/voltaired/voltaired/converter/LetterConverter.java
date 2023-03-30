package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.LetterModel;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LetterConverter implements Converter<LetterModel, LetterEntity> {

    @Override public LetterEntity convertNotNull(LetterModel input) {
        return new LetterEntity()
                .withId(input.id)
                .withContent(input.content)
                .withDate(input.date)
                .withSubject(input.subject)
                .withWriter(null)
                .withCircle(null)
                ;
    }
}
