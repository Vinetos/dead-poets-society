package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.ErrorCodes;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.domain.service.CircleService;
import com.voltaired.voltaired.presentation.CircleApi;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped public class CircleResource implements CircleApi {

    @Inject CircleService circleService;

    @Override public List<getAllCircles.Response> getAllActivities() {
        return circleService.getCircles().stream().map(circle -> new getAllCircles.Response(
                circle.getId(),
                circle.getName(),
                circle.getWriters().transactionalGet().stream().map(WriterEntity::getId).toList(),
                circle.getLetters().transactionalGet().stream().map(LetterEntity::getId).toList()
        )).toList();
    }

    @Override public getCircle.Response getCircle(Long id) {
        val opt = circleService.getCircle(id).map(circle -> new getCircle.Response(
                circle.getId(),
                circle.getName(),
                circle.getWriters().transactionalGet().stream().map(WriterEntity::getId).toList(),
                circle.getLetters().transactionalGet().stream().map(LetterEntity::getId).toList()
        ));
        if (opt.isEmpty()) throw ErrorCodes.CIRCLE_NOT_FOUND.with(id).get();
        return opt.get();
    }
}
