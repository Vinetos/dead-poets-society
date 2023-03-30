package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.LetterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class LetterRepository implements PanacheRepository<LetterModel> {

    public List<LetterModel> findInCircle(Long circleId) {
        return find("?1 member of circles", circleId).stream().toList();
    }

}
