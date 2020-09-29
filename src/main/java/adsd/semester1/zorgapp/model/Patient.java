package adsd.semester1.zorgapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "patients")
public class Patient {
    private long _id;
    private String _firstName;
    private String _lastName;
    private int _age;
    private double _weight;
    private double _length;

    public Patient() {

    }

    public Patient(String firstName, String lastName) {
        _firstName = firstName;
        _lastName = lastName;
    }

    public Patient(long id, String firstName, String lastName, int age, double weight, double length) {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _age = age;
        _weight = weight;
        _length = length;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return _id;
    }
    public void setId(long id) {
        _id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return _firstName;
    }
    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return _lastName;
    }
    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    @Column(name = "age", nullable = true)
    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        _age = age;
    }

    @Column(name = "weight", nullable = true)
    public double getWeight() {
        return _weight;
    }

    public void setWeight(double weight) {
        _weight = weight;
    }

    @Column(name = "length", nullable = true)
    public double getLength() {
        return _length;
    }

    public void setLength(double length) {
        _length = length;
    }

    @Transient
    public double getBmi() {
        return _weight / Math.pow((_length / 100.0), 2.0);
    }
}
