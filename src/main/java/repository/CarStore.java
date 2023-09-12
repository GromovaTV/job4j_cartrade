package repository;

import model.Car;
import java.util.List;

public interface CarStore {
    List<Car> findCars(String[] brands, String[] bodies);
}
