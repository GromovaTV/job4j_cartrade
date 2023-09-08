package repository;

import model.Ads;
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
                            "join fetch a.photos");
                    List<Ads> list = query.list();
                    return list;
                }
        );
    }

    @Override
    public Ads findAdsById(int id) {
        return null;
    }
}
