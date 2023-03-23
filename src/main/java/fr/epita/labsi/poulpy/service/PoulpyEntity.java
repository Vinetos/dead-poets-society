package fr.epita.labsi.poulpy.service;

import fr.epita.labsi.poulpy.IPoulpy;
import fr.epita.labsi.poulpy.repository.PoulpyModel;
import fr.epita.labsi.poulpy.resource.PoulpyDTO;

public class PoulpyEntity {

    private String name;
    private IPoulpy.Color color;

    public PoulpyEntity(String name, IPoulpy.Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public IPoulpy.Color getColor() {
        return color;
    }

    public static PoulpyEntity fromModel(PoulpyModel model) {
        return new PoulpyEntity(model.name, model.color);
    }

    public static PoulpyEntity fromDTO(PoulpyDTO dto) {
        return new PoulpyEntity(dto.getName(), dto.getColor());

    }


}
