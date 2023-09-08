package repository;

import model.Ads;
import java.util.List;

public interface AdsStore {
    List<Ads> findAllAds();
    Ads findAdsById(int id);
}
