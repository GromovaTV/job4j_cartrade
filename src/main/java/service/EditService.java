package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import repository.AdsRepository;
import repository.CarRepository;
import repository.UserRepository;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EditService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START EDIT SERVLET get");
        resp.setContentType("application/json; charset=utf-8");
        ServletContext context = req.getServletContext();
        AdsDTO adsDTO = new AdsDTO();
        if (context.getAttribute("id") != null) {
            int id = Integer.parseInt(context.getAttribute("id").toString());
            Ads ads = AdsRepository.instOf().findAdsById(id);
            adsDTO.setBrand(ads.getCar().getBrand().getName());
            adsDTO.setBody(ads.getCar().getBody().getName());
            adsDTO.setPrice(ads.getPrice());
            adsDTO.setDescription(ads.getDescription());
            adsDTO.setSold(ads.isSold());
            adsDTO.setCreated(ads.getCreated());
        }
        String json = GSON.toJson(adsDTO);
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("FINISH EDIT SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START SAVE");
        AdsDTO adsDTO = GSON.fromJson(req.getReader(), AdsDTO.class);
        System.out.println(adsDTO.toString());
        var context = req.getServletContext();
        HttpSession sc = req.getSession();
        Object id = context.getAttribute("id");
        String brandName = adsDTO.getBrand();
        String bodyName = adsDTO.getBody();
        int price = adsDTO.getPrice();
        String desc = adsDTO.getDescription();
        boolean sold = adsDTO.isSold();
        User user = (User) sc.getAttribute("user");
        Brand brand = CarRepository.instOf().findBrand(brandName);
        Body body = CarRepository.instOf().findBody(bodyName);
        Car car = Car.of(brand, body);
        Ads ads = Ads.of(desc, sold, price, car, user);
        if (id != null) {
            Ads a = AdsRepository.instOf().findAdsById(Integer.parseInt(id.toString()));
            List<Photo> photos = a.getPhotos();
            ads.setPhotos(photos);
            Car c = a.getCar();
            car.setId(c.getId());
            ads.setId(a.getId());
        }
        CarRepository.instOf().saveOrUpdate(car);
        AdsRepository.instOf().saveOrUpdate(ads);
        System.out.println("FINISH SAVE");
    }
}
