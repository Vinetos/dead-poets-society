package com.voltaired.voltaired.converter;

import com.voltaired.voltaired.data.model.WriterModel;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.util.Converter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WriterConverter implements Converter<WriterModel, WriterEntity> {

    @Override
    public WriterEntity convertNotNull(WriterModel input) {
        return new WriterEntity();
    }
}
