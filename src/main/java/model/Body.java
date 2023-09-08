package model;

import javax.persistence.*;

@Entity
@Table(name = "bodies")
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Body of(String name) {
        Body body = new Body();
        body.setName(name);
        return body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Body{"
                + "id=" + id + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
