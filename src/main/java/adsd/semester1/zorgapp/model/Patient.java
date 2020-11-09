package adsd.semester1.zorgapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "patient")
public class Patient {

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Weight> weights = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "length")
    private double length;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(table = "patient", name = "patient_id"),
            inverseJoinColumns = @JoinColumn(table = "medicine", name = "medicine_id")
    )
    private Set<Medicine> medicines = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Transient
    public Double getBmi() {
        Double weight = getLatestWeight();
        if (weight != null) {
            return getLatestWeight() / Math.pow((length / 100.0), 2.0);
        }
        return null;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
        medicine.getPatients().add(this);
    }

    public void removeMedicine(int medicineId) {
        var medicine = medicines.stream()
                .filter(x -> x.getId() == medicineId).findFirst().orElse(null);
        if (medicine != null) {
            medicines.remove(medicine);
            medicine.getPatients().remove(this);
        }
    }

    public Set<Weight> getWeights() {
        return this.weights.stream().sorted(Comparator.comparing(Weight::getCreate_date).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void removeWeight(int weightId) {
        getWeights().stream()
                .filter(x -> x.getId() == weightId).findFirst().ifPresent(weight -> weights.remove(weight));
    }

    @Transient
    public Double getLatestWeight() {
        var weights = getWeights();
        if (weights.isEmpty()) {
            return null;
        }
        return weights.stream().findFirst().get().getWeight();
    }

    @Transient
    public String getWeightInJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var weights = getWeights();
        return objectMapper.writeValueAsString(weights);
    }

    @Transient
    public void setWeights(Set<Weight> weights) {
        this.weights = weights;
    }

    @Transient
    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }
}
