package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.data.model.WriterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class WriterRepository implements PanacheRepository<WriterModel> {

    @Inject CircleRepository circleRepository;

    public List<WriterModel> findInCircle(Long circleId) {
        return find("?1 member of circles", circleId).stream().toList();
    }

    public List<CircleModel> enterCircle(Long circleId, Long writerId) {
        val circle = findById(writerId);
        circle.circles.add(circleRepository.findById(circleId));
        return circle.circles;
    }

    public List<CircleModel> leaveCircle(Long circleId, Long writerId) {
        val circle = findById(writerId);
        circle.circles.remove(circleRepository.findById(circleId));
        return circle.circles;
    }

}
