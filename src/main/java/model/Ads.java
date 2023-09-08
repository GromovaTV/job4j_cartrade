package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean sold;
    private int price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ad_id")
    private List<Photo> photos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Ads of(String description, boolean sold, int price, Car car, User user) {
        Ads ads = new Ads();
        ads.setDescription(description);
        ads.setSold(sold);
        ads.setCar(car);
        ads.setUser(user);
        ads.setCreated(new Date(System.currentTimeMillis()));
        return ads;
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Ad{"
                + "id=" + id + '\''
                + ", description='" + description + '\''
                + ", sold=" + sold + '\''
                + ", created=" + created + '\''
                + ", car=" + car + '\''
                + ", photos=" + photos + '\''
                + ", user=" + user + '\''
                + '}';
    }
}