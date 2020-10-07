package adsd.semester1.zorgapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = true)
    private int age;

    @Column(name = "weight", nullable = true)
    private double weight;

//    @OneToMany(mappedBy="weight", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    private Set<Weight> weight;

    @Column(name = "length", nullable = true)
    private double length;

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

//    public Set<Weight> getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Set<Weight> weight) {
//        this.weight = weight;
//    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Transient
    public double getBmi() {
        return getWeight() / Math.pow((length / 100.0), 2.0);
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(table = "patient", name = "patient_id"),
            inverseJoinColumns = @JoinColumn(table = "medicine", name = "medicine_id")
    )
    private Set<Medicine> medicines = new HashSet<>();

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
}
