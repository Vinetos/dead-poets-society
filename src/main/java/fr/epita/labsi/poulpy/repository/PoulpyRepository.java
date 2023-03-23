package fr.epita.labsi.poulpy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PoulpyRepository implements PanacheRepositoryBase<PoulpyModel, String> {


}
