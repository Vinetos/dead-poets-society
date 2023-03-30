package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.domain.service.CircleService;
import com.voltaired.voltaired.presentation.CircleApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;

@ApplicationScoped public class CircleResource implements CircleApi {

    @Inject CircleService circleService;

    @Override public List<getAllCircles.Response> getAllActivities() {
        return circleService.getCircles().map(cirle -> new getAllCircles.Response(
                cirle.getId(),
                cirle.getName(),
                new HashSet<>(),
                cirle.getWriter().getId()
        )).toList();
    }
}
