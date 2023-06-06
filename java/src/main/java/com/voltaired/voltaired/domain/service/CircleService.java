package com.voltaired.voltaired.domain.service;

import com.voltaired.voltaired.converter.CircleConverter;
import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.data.repository.CircleRepository;
import com.voltaired.voltaired.domain.entity.CircleEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped public class CircleService {

    @Inject CircleRepository circleRepository;
    @Inject CircleConverter circleConverter;

    @Transactional
    public List<CircleEntity> getCircles() {
        return circleRepository.findAll()
                               .stream()
                               .map(circleConverter::convert)
                               .toList();
    }

    @Transactional
    public Optional<CircleEntity> getCircle(Long id) {
        return circleRepository.findByIdOptional(id).map(circleConverter::convert);
    }

    @Transactional
    public CircleEntity createCircle(String name) {
        var circleModel = new CircleModel().withName(name);
        circleRepository.persist(circleModel);
        return circleConverter.convert(circleModel);
    }

    public void deleteCircle(Long id) {
        circleRepository.deleteById(id);
    }
}
