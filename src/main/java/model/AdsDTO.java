package model;

import java.util.Date;

public class AdsDTO {
    private String brand;
    private String body;
    private int price;
    private String description = "";
    private boolean sold;
    private Date created;
    private boolean user;

    public AdsDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AdsDTO{"
                + "brand='" + brand + '\''
                + ", body='" + body + '\''
                + ", price='" + price + '\''
                + ", description='" + description + '\''
                + ", sold=" + sold
                + ", created=" + created
                + ", user=" + user
                + '}';
    }
}
