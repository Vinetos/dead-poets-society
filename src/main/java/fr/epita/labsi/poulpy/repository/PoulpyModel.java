package fr.epita.labsi.poulpy.repository;

import fr.epita.labsi.poulpy.IPoulpy;
import fr.epita.labsi.poulpy.service.PoulpyEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poulpy")
public class PoulpyModel extends PanacheEntityBase {

    public @Id
    String name;
    public @NotNull
    IPoulpy.Color color;

    public PoulpyModel(String name, IPoulpy.Color color) {
        this.name = name;
        this.color = color;
    }

    public PoulpyModel() {
    }

    public static PoulpyModel fromEntity(PoulpyEntity entity) {
        return new PoulpyModel(entity.getName(), entity.getColor());
    }

}
