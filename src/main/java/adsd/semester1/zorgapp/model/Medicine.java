package adsd.semester1.zorgapp.model;

import javax.persistence.*;

@Entity
@Table(name = "medicines")
public class Medicine {
    private long _id;
    private String _name;
    private String _description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long get_id() {
        return _id;
    }

    public void set_id(long id) {
        _id = id;
    }

    @Column(name = "name", nullable = false)
    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        _name = name;
    }

    @Column(name = "description", nullable = false)
    public String get_description() {
        return _description;
    }

    public void set_description(String description) {
        _description = description;
    }
}
