package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.WriterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class WriterRepository implements PanacheRepository<WriterModel> {

    public List<WriterModel> findInCircle(Long circleId) {
        return find("?1 member of circles", circleId).stream().toList();
    }

}
