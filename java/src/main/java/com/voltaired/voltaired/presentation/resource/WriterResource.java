package com.voltaired.voltaired.presentation.resource;

import com.voltaired.voltaired.ErrorCodes;
import com.voltaired.voltaired.domain.service.WriterService;
import com.voltaired.voltaired.presentation.WriterApi;
import com.voltaired.voltaired.util.auth.AuthenticationContext;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static com.voltaired.voltaired.util.Optionals.opt;

@ApplicationScoped public class WriterResource implements WriterApi {

    @Inject WriterService writerService;
    @Inject AuthenticationContext authenticationContext;

    @Override public List<getAllWriters.Response> getAllWriters() {
        System.out.println("User: " + authenticationContext.getCurrentUser().getEmail());
        return writerService.getWriters().stream().map(writer -> new getAllWriters.Response(
                writer.getId(),
                writer.getTitle(),
                writer.getName(),
                writer.getPenName(),
                writer.getLetters().get(),
                writer.getCircles().get()
        )).toList();
    }

    @Override public getWriter.Response getWriter(Long id) {
        val opt = writerService.getWriter(id).map(writer -> new WriterApi.getWriter.Response(
                writer.getId(),
                writer.getTitle(),
                writer.getName(),
                writer.getPenName(),
                writer.getLetters().get(),
                writer.getCircles().get()
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
