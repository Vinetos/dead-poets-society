package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.ErrorCodes;
import com.voltaired.voltaired.domain.service.WriterService;
import com.voltaired.voltaired.presentation.WriterApi;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static com.voltaired.voltaired.util.Optionals.opt;

@ApplicationScoped public class WriterResource implements WriterApi {

    @Inject WriterService writerService;

    @Override public List<getAllWriters.Response> getAllWriters() {
        return writerService.getWriters().map(writer -> new getAllWriters.Response(
                writer.getId(),
                writer.getTitle(),
                writer.getName(),
                writer.getPenName(),
                writer.getLetters().transactionalGet(),
                writer.getCircles().transactionalGet()
        )).toList();
    }

    @Override public getWriter.Response getWriter(Long id) {
        val opt = writerService.getWriter(id).map(writer -> new WriterApi.getWriter.Response(
                writer.getId(),
                writer.getTitle(),
                writer.getName(),
                writer.getPenName(),
                writer.getLetters().transactionalGet(),
                writer.getCircles().transactionalGet()
        ));
        if (opt.isEmpty()) throw ErrorCodes.WRITER_NOT_FOUND.with(id).get();
        return opt.get();
    }

    @Override public createWriter.Response createWriter(createWriter.Request request) {
        val opt = opt(writerService.createWriter(request)).map(writer -> new WriterApi.createWriter.Response(
                writer.getId(),
                writer.getName(),
                writer.getPenName(),
                writer.getTitle()
        ));
        return opt.get();
    }
}
