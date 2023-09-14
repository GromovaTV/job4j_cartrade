package repository;

import model.Ads;
import model.Car;
import java.util.List;

public interface AdsStore {
    List<Ads> findAllAds();
    List<Ads> findByCarAndPrice(List<Car> cars, String minPrice, String maxPrice);
    List<Ads> findByPrice(String minPrice, String maxPrice);
    Ads findAdsById(int id);
    void saveOrUpdate(Ads ads);
}
