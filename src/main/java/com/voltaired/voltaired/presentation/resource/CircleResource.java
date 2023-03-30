package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.domain.service.CircleService;
import com.voltaired.voltaired.presentation.CircleApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;

import static com.voltaired.voltaired.util.Optionals.opt;

@ApplicationScoped public class CircleResource implements CircleApi {

    @Inject CircleService circleService;

    @Override public List<getAllCircles.Response> getAllActivities() {
        return circleService.getCircles().map(circle -> new getAllCircles.Response(
                circle.getId(),
                circle.getName(),
                new HashSet<>(),
                opt(circle.getWriter()).map(WriterEntity::getId).orElse(-1L)
        )).toList();
    }

    @Override public getCircle.Response getCircle(Long id) {
        return null;
    }
}
