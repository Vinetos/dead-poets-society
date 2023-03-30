package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.LetterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LetterRepository implements PanacheRepository<LetterModel> {

}
