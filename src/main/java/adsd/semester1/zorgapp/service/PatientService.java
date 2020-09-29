package adsd.semester1.zorgapp.service;

import adsd.semester1.zorgapp.model.Patient;
import adsd.semester1.zorgapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public List<Patient> listAll() {
        return repo.findAll();
    }

    public void save(Patient patient) {
        repo.save(patient);
    }

    public Patient get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
