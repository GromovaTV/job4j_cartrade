package repository;

import model.Body;
import model.Brand;
import model.Car;
import org.hibernate.query.Query;
import java.util.List;

public class CarRepository extends CommonRepository implements CarStore {
    private static final class Lazy {
        private static CarStore INST;
    }

    private CarRepository() {
    }

    public static CarStore instOf() {
        if (Lazy.INST == null) {
            Lazy.INST = new CarRepository();
        }
        return Lazy.INST;
    }

    private List<Car> findByBrand(String[] brands) {
        return this.tx(
                session -> {
                        Query query = session.createQuery("select distinct c from Car c " +
                                "join fetch c.brand " +
                                "join fetch c.body " +
                                "where c.brand.name in (:brands)");
                        query.setParameterList("brands", brands);
                        List<Car> list = query.list();
                    return list;
                }
        );
    }

    private List<Car> findByBody(String[] bodies) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct c from Car c " +
                            "join fetch c.brand " +
                            "join fetch c.body " +
                            "where c.body.name in (:bodies)");
                    query.setParameterList("bodies", bodies);
                    List<Car> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public List<Car> findCars(String[] brands, String[] bodies) {
        if (brands.length == 0) {
            return findByBody(bodies);
        }
        if (bodies.length == 0) {
            return findByBrand(brands);
        }
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct c from Car c " +
                            "join fetch c.brand " +
                            "join fetch c.body " +
                            "where c.brand.name in (:brands) " +
                            "and c.body.name in (:bodies)");
                    query.setParameterList("brands", brands);
                    query.setParameterList("bodies", bodies);
                    List<Car> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public Brand findBrand(String brandName) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct b from Brand b " +
                            "where b.name =:name");
                    query.setParameter("name", brandName);
                    Brand brand = (Brand) query.uniqueResult();
                    return brand;
                }
        );
    }

    @Override
    public Body findBody(String bodyName) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select distinct b from Body b " +
                            "where b.name =:name");
                    query.setParameter("name", bodyName);
                    Body body = (Body) query.uniqueResult();
                    return body;
                }
        );
    }

    @Override
    public void saveOrUpdate(Car car) {
        this.tx(
                session -> {
                    session.saveOrUpdate(car);
                    return car;
                }
        );
    }
}
