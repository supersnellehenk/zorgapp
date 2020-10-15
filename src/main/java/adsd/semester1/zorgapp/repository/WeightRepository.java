package adsd.semester1.zorgapp.repository;

import adsd.semester1.zorgapp.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

}