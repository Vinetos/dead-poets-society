package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.CircleConverter;
import com.voltaired.voltaired.converter.LetterConverter;
import com.voltaired.voltaired.converter.WriterConverter;
import com.voltaired.voltaired.data.model.WriterModel;
import com.voltaired.voltaired.data.repository.WriterRepository;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.presentation.WriterApi;

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

    public List<WriterEntity> getWriters() {
        return writerRepository.findAll()
                               .stream()
                               .map(writer -> new WriterEntity().withId(writer.id)
                                                                .withTitle(writer.title)
                                                                .withName(writer.name)
                                                                .withPenName(writer.penName)
                                                                .withCircles(() -> writer.circles.stream()
                                                                                                 .map(circleConverter::convert)
                                                                                                 .toList())
                                                                .withLetters(() -> writer.letters.stream()
                                                                                                 .map(letterConverter::convert)
                                                                                                 .toList())).toList();
    }

    public Optional<WriterEntity> getWriter(Long id) {
        return writerRepository.findByIdOptional(id).map(writerConverter::convert);
    }

    @Transactional
    public WriterEntity createWriter(WriterApi.createWriter.Request request) {
        var writerModel = new WriterModel().withName(request.name)
                                           .withPenName(request.penName)
                                           .withTitle(request.title);
        writerRepository.persist(writerModel);
        return writerConverter.convert(writerModel);
    }

    @Transactional
    public List<Long> enterCircle(Long circleId, Long writerId) {
        return writerRepository.enterCircle(circleId, writerId).stream().map(circle -> circle.id).toList();
    }

    @Transactional
    public List<Long> leaveCircle(Long circleId, Long writerId) {
        return writerRepository.leaveCircle(circleId, writerId).stream().map(circle -> circle.id).toList();
    }
}
