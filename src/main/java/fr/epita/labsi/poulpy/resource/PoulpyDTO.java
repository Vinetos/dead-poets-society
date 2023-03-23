package fr.epita.labsi.poulpy.resource;

import fr.epita.labsi.poulpy.IPoulpy.Color;
import fr.epita.labsi.poulpy.service.PoulpyEntity;
import lombok.Getter;

@Getter
public final class PoulpyDTO {

    private final String name;
    private final Color color;

    public PoulpyDTO(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public static PoulpyDTO fromModel(PoulpyEntity entity) {
        return new PoulpyDTO(entity.getName(), entity.getColor());
    }

}
