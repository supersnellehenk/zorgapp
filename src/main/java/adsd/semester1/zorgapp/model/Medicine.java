package adsd.semester1.zorgapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "kind", nullable = false)
    private String kind;
    @Column(name = "dose", nullable = false)
    private String dose;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
    }

    @ManyToMany(mappedBy = "medicines", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Patient> patients = new HashSet<>();

    public Set<Patient> getPatients() {
        return patients;
    }
}
