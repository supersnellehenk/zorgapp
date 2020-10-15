package adsd.semester1.zorgapp.service;

import adsd.semester1.zorgapp.model.Weight;
import adsd.semester1.zorgapp.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WeightService {

    @Autowired
    private WeightRepository repo;

    public List<Weight> listAll() {
        return repo.findAll();
    }

    public Weight save(Weight weight) {
        return repo.save(weight);
    }

    public Weight get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
