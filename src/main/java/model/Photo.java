package model;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;

    public static Photo of(String path) {
        Photo photo = new Photo();
        photo.setPath(path);
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Photo{"
                + "id=" + id + '\''
                + ", path='" + path + '\''
                + '}';
    }
}
