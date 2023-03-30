package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.LetterConverter;
import com.voltaired.voltaired.data.repository.LetterRepository;
import com.voltaired.voltaired.domain.entity.LetterEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class LetterService {

    @Inject LetterRepository letterRepository;
    @Inject LetterConverter converter;

    @Transactional
    public List<LetterEntity> findInCircle(Long circleId) {
        return letterRepository.findInCircle(circleId).stream().map(converter::convertNotNull).toList();
    }

}
