package com.voltaired.voltaired.data;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WriterRepository implements PanacheRepository<WriterModel> {

}
