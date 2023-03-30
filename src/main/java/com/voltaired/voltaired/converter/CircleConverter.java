package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped public class CircleConverter implements Converter<CircleModel, CircleEntity> {

    @Override public CircleEntity convertNotNull(CircleModel input) {
        return new CircleEntity();
    }
}
