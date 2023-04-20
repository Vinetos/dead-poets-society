package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.CircleConverter;
import com.voltaired.voltaired.converter.LetterConverter;
import com.voltaired.voltaired.converter.WriterConverter;
import com.voltaired.voltaired.data.repository.WriterRepository;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.presentation.CircleApi;
import com.voltaired.voltaired.util.Seq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped public class WriterService {

    @Inject WriterRepository writerRepository;
    @Inject WriterConverter writerConverter;
    @Inject CircleConverter circleConverter;
    @Inject LetterConverter letterConverter;

    public Seq<WriterEntity> getWriters() {
        return () -> writerRepository.findAll()
                                     .stream()
                                     .limit(5)
                                     .map(writer -> new WriterEntity().withId(writer.id)
                                                                      .withTitle(writer.title)
                                                                      .withName(writer.name)
                                                                      .withPenName(writer.penName)
                                                                      .withCircles(() -> writer.circles.stream()
                                                                                                       .map(circleConverter::convertNotNull)
                                                                                                       .toList())
                                                                      .withLetters(() -> writer.letters.stream()
                                                                                                       .map(letterConverter::convertNotNull)
                                                                                                       .toList()));
    }

    public Optional<WriterEntity> getWriter(Long id) {
        return writerRepository.findByIdOptional(id).map(writerConverter::convertNotNull);
    }

    @Transactional
    public List<Long> enterCircle(Long circleId, Long writerId) {
        return writerRepository.enterCircle(circleId, writerId).stream().map(circle -> circle.id).toList();
    }

    @Transactional
    public void leaveCircle(Long circleId, Long writerId) {
        writerRepository.leaveCircle(circleId, writerId);
    }
}
