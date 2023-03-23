package fr.epita.labsi.poulpy.resource;

import fr.epita.labsi.poulpy.IPoulpy.Color;
import fr.epita.labsi.poulpy.service.PoulpyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/poulpy")
@Produces(MediaType.APPLICATION_JSON)
public final class PoulpyResource {

    @Inject
    PoulpyService poulpyService;

    @GET
    public List<PoulpyDTO> getPoulpys() {
        // DTO -> ENTITY -> MODEl
        return poulpyService.getPoulpies().stream().map(PoulpyDTO::fromModel).toList();
    }

    @PUT
    public PoulpyDTO addPoulpy(PoulpyDTO poulpyDTO) {
        poulpyService.addPoulpy(poulpyDTO);
        return poulpyDTO;
    }

    @GET
    @Path("/{color}")
    public List<PoulpyDTO> getPoulpy(@PathParam("color") Color color) {
        return getPoulpys().stream().filter(p -> p.getColor() == color).toList();
    }

    @DELETE
    @Path("/{color}")
    public void deletePoulpy(@PathParam("color") Color color) {
        poulpyService.deletePoulpy(color);
    }

}
