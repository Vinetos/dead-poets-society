package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.CircleConverter;
import com.voltaired.voltaired.data.repository.CircleRepository;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.util.Seq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped public class CircleService {

    @Inject CircleRepository circleRepository;
    @Inject CircleConverter circleConverter;

    public Seq<CircleEntity> getCircles() {
        return () -> circleRepository.findAll()
                                     .stream()
                                     .limit(5)
                                     .map(circle -> new CircleEntity().withId(circle.id)
                                                                      .withLetters(Seq.of())
                                                                      .withWriter(null)
                                                                      .withName(circle.name));
    }

    public Optional<CircleEntity> getCircle(Long id) {
        return circleRepository.findByIdOptional(id).map(circleConverter::convertNotNull);
    }
}
