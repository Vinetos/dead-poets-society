package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.LetterConverter;
import com.voltaired.voltaired.data.model.LetterModel;
import com.voltaired.voltaired.data.repository.CircleRepository;
import com.voltaired.voltaired.data.repository.LetterRepository;
import com.voltaired.voltaired.data.repository.WriterRepository;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.presentation.CircleApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LetterService {

    @Inject LetterRepository letterRepository;
    @Inject LetterConverter converter;

    @Inject
    WriterRepository writerRepository;

    @Inject
    CircleRepository circleRepository;

    @Transactional
    public List<LetterEntity> findInCircle(Long circleId) {
        return letterRepository.findInCircle(circleId).stream().map(converter::convertNotNull).toList();
    }

    @Transactional
    public List<LetterEntity> getLetters() {
        return letterRepository.findAll()
                               .stream()
                               .limit(5)
                               .map(converter::convertNotNull)
                               .toList();
    }

    public Optional<LetterEntity> getLetter(Long id) {
        return letterRepository.findByIdOptional(id).map(converter::convertNotNull);
    }

    public LetterEntity postLetters(Long circleId, CircleApi.postLetters.Request request) {
        var letterModel = new LetterModel()
                .withDate(ZonedDateTime.now())
                .withSubject(request.subject)
                .withContent(request.content)
                .withCircles(Collections.singletonList(circleRepository.findById(circleId)))
                .withWriter(writerRepository.findById(request.writerId));
        letterRepository.persist(letterModel);
        return converter.convert(letterModel);
    }

}
