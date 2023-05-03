package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.ErrorCodes;
import com.voltaired.voltaired.domain.entity.CircleEntity;
import com.voltaired.voltaired.domain.service.LetterService;
import com.voltaired.voltaired.presentation.LetterApi;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped public class LetterResource implements LetterApi {

    @Inject LetterService letterService;

    @Override public List<getAllLetters.Response> getAllLetters() {
        return letterService.getLetters().stream().map(letter -> new getAllLetters.Response(
                letter.getId(),
                letter.date,
                letter.getSubject(),
                letter.getContent(),
                letter.getCircle().transactionalGet().stream().map(CircleEntity::getId).toList(),
                letter.getWriter().getId()
        )).toList();
    }

    @Override public getLetter.Response getLetter(Long id) {
        val opt = letterService.getLetter(id).map(letter -> new getLetter.Response(
                letter.getId(),
                letter.date,
                letter.getSubject(),
                letter.getContent(),
                letter.getCircle().transactionalGet().stream().map(CircleEntity::getId).toList(),
                letter.getWriter().getId()
        ));
        if (opt.isEmpty()) throw ErrorCodes.LETTER_NOT_FOUND.with(id).get();
        return opt.get();
    }
}
