package repository;

import model.Body;
import model.Brand;
import model.Car;
import java.util.List;

public interface CarStore {
    List<Car> findCars(String[] brands, String[] bodies);
    Brand findBrand(String brandName);
    Body findBody(String bodyName);
    void save(Car car);
}
