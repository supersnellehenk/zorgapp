package adsd.semester1.zorgapp.service;

import adsd.semester1.zorgapp.model.Medicine;
import adsd.semester1.zorgapp.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicineService {

    @Autowired
    private MedicineRepository repo;

    public List<Medicine> listAll() {
        return repo.findAll();
    }

    public void save(Medicine medicine) {
        repo.save(medicine);
    }

    public Medicine get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
