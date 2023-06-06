package com.voltaired.voltaired.data.repository;

import com.voltaired.voltaired.data.model.CircleModel;
import com.voltaired.voltaired.data.model.WriterModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class WriterRepository implements PanacheRepository<WriterModel> {

    @Inject CircleRepository circleRepository;

    public List<WriterModel> findInCircle(Long circleId) {
        return find("?1 member of circles", circleId).stream().toList();
    }

    public List<CircleModel> enterCircle(Long circleId, Long writerId) {
        val writer = findById(writerId);
        val circle = circleRepository.findById(circleId);
        if (!writer.circles.contains(circle))
            writer.circles.add(circle);
        return writer.circles;
    }

    public List<CircleModel> leaveCircle(Long circleId, Long writerId) {
        val writer = findById(writerId);
        val circle = circleRepository.findById(circleId);
        if (!writer.circles.contains(circle))
            writer.circles.remove(circle);
        return writer.circles;
    }

}
