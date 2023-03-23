package fr.epita.labsi.poulpy.service;

import fr.epita.labsi.poulpy.IPoulpy;
import fr.epita.labsi.poulpy.repository.PoulpyModel;
import fr.epita.labsi.poulpy.repository.PoulpyRepository;
import fr.epita.labsi.poulpy.resource.PoulpyDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PoulpyService {

    @Inject
    PoulpyRepository poulpyRepository;


    public List<PoulpyEntity> getPoulpies() {
        return poulpyRepository.findAll()
                .stream()
                .map(PoulpyEntity::fromModel)
                .toList();
    }

    @Transactional
    public PoulpyEntity addPoulpy(PoulpyDTO poulpyDTO) {
        var entity = PoulpyEntity.fromDTO(poulpyDTO);
        poulpyRepository.persist(PoulpyModel.fromEntity(entity));
        return entity;
    }

    public List<PoulpyEntity> getPoulpy(IPoulpy.Color color) {
        return poulpyRepository.find("color", color).stream().map(PoulpyEntity::fromModel).toList();
    }

    @Transactional
    public void deletePoulpy(IPoulpy.Color color) {
        poulpyRepository.delete("color", color);
    }

}
