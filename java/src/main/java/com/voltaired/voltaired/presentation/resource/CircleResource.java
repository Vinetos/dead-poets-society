package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.ErrorCodes;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.domain.entity.LetterEntity;
import com.voltaired.voltaired.domain.entity.WriterEntity;
import com.voltaired.voltaired.domain.service.CircleService;
import com.voltaired.voltaired.domain.service.LetterService;
import com.voltaired.voltaired.domain.service.WriterService;
import com.voltaired.voltaired.presentation.CircleApi;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static com.voltaired.voltaired.util.Optionals.opt;

@ApplicationScoped public class CircleResource implements CircleApi {

    @Inject CircleService circleService;
    @Inject LetterService letterService;
    @Inject WriterService writerService;

    @Override public List<getAllCircles.Response> getAllCircles() {
        return circleService.getCircles().stream().map(circle -> new getAllCircles.Response(
                circle.getId(),
                circle.getName(),
                circle.getLetters().stream().map(LetterEntity::getId).toList(),
                circle.getWriters().stream().map(WriterEntity::getId).toList()
                )).toList();
    }

    @Override public getCircle.Response getCircle(Long id) {
        val opt = circleService.getCircle(id).map(circle -> new getCircle.Response(
                circle.getId(),
                circle.getName(),
                circle.getLetters().stream().map(LetterEntity::getId).toList(),
                circle.getWriters().stream().map(WriterEntity::getId).toList()
                ));
        if (opt.isEmpty()) throw ErrorCodes.CIRCLE_NOT_FOUND.with(id).get();
        return opt.get();
    }

    @Override
    public postLettersReply.Response postLetterReply(Long id, Long letterId, postLettersReply.Request request) {
        // TODO: 26/05/2023 Implements me
        return null;
    }

    @Override public void deleteCircles(Long id) {
        circleService.deleteCircle(id);
    }

    @Override
    public CircleApi.createCircle.Response createCircle(createCircle.Request request) {
        val opt = opt(circleService.createCircle(request.name)).map(circle -> new CircleApi.createCircle.Response(
                circle.getId(),
                circle.getName()
        ));
        return opt.get();
    }

    @Override public enterCircle.Response enterCircle(Long id, enterCircle.Request request) {
        return new enterCircle.Response(writerService.enterCircle(id, request.writerId));
    }

    @Override public leaveCircle.Response leaveCircle(Long id, leaveCircle.Request request) {
        return new leaveCircle.Response(writerService.leaveCircle(id, request.writerId));
    }

    @Override public postLetter.Response postLetter(Long circleId, postLetter.Request request) {
        val opt = opt(letterService.postLetters(circleId, request)).map(letter -> new postLetter.Response(
                letter.getId(),
                letter.getDate(),
                letter.getCircle().stream().map(CircleEntity::getId).toList().get(0),
                letter.getSubject(),
                letter.getContent(),
                letter.writer.getId()
        ));
        return opt.get();
    }
}
