package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.data.model.WriterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CircleRepository implements PanacheRepository<CircleModel> {

}
