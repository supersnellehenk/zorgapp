package adsd.semester1.zorgapp.service;

import adsd.semester1.zorgapp.model.Patient;
import adsd.semester1.zorgapp.model.Weight;
import adsd.semester1.zorgapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public List<Patient> listAll() {
        return repo.findAll();
    }

    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    public Patient get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Set<Weight> getWeights(long id) {
        return repo.findById(id).get().getWeights();
    }

}
