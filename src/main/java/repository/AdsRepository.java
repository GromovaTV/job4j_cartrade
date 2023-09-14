package repository;

import model.Ads;
import model.Car;
import org.hibernate.query.Query;
import java.util.List;

public class AdsRepository extends CommonRepository implements AdsStore {

    private static final class Lazy {
        private static AdsStore INST;
    }

    private AdsRepository() {
    }

    public static AdsStore instOf() {
        if (Lazy.INST == null) {
            Lazy.INST = new AdsRepository();
        }
        return Lazy.INST;
    }

    @Override
    public List<Ads> findAllAds() {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a from Ads a " +
                            "join fetch a.car c " +
                            "join fetch c.brand " +
                            "join fetch c.body " +
                            "join fetch a.user " +
                            "left join fetch a.photos");
                    List<Ads> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public List<Ads> findByPrice(String minPrice, String maxPrice) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a from Ads a " +
                            "join fetch a.car c " +
                            "join fetch c.brand " +
                            "join fetch c.body " +
                            "join fetch a.user " +
                            "left join fetch a.photos " +
                            "where a.price BETWEEN :minPrice AND :maxPrice");
                    query.setParameter("minPrice", Integer.parseInt(minPrice));
                    query.setParameter("maxPrice", Integer.parseInt(maxPrice));
                    List<Ads> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public List<Ads> findByCarAndPrice(List<Car> cars, String minPrice, String maxPrice) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a from Ads a " +
                            "join fetch a.car c " +
                            "join fetch c.brand " +
                            "join fetch c.body " +
                            "join fetch a.user " +
                            "left join fetch a.photos " +
                            "where a.price BETWEEN :minPrice AND :maxPrice "
                            + "and c in (:cars)");
                    query.setParameterList("cars", cars);
                    query.setParameter("minPrice", Integer.parseInt(minPrice));
                    query.setParameter("maxPrice", Integer.parseInt(maxPrice));
                    List<Ads> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public Ads findAdsById(int id) {
        return this.tx(
                session -> {
                    final Query query = session.createQuery("select distinct a from Ads a " +
                            "join fetch a.car c " +
                                    "join fetch c.brand " +
                                    "join fetch c.body " +
                                    "join fetch a.user " +
                                    "left join fetch a.photos " +
                            "where a.id=:id");
                    query.setParameter("id", id);
                    return (Ads) query.uniqueResult();
                }
        );
    }

    @Override
    public void save(Ads ads) {
        this.tx(
                session -> {
                    session.save(ads);
                    return ads;
                }
        );
    }
}
