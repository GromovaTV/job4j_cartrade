package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.Car;
import model.SearchFilter;
import model.User;
import repository.AdsRepository;
import repository.CarRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFilterService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START SearchFilter SERVLET");
        SearchFilter searchFilter = GSON.fromJson(req.getReader(), SearchFilter.class);
        System.out.println(searchFilter.toString());
        List<Car> cars;
        List<Ads> res;
        String min = searchFilter.getMin();
        String max = searchFilter.getMax();
        String[] brands = searchFilter.getBrands();
        String[] bodies = searchFilter.getBodies();
        System.out.println(Arrays.toString(brands));
        System.out.println(Arrays.toString(bodies));
        if (brands.length == 0 && bodies.length == 0) {
            res = AdsRepository.instOf().findByPrice(min, max);
        } else {
            cars = CarRepository.instOf().findCars(brands, bodies);
            res = AdsRepository.instOf().findByCarAndPrice(cars, min, max);
        }
        System.out.println(res);
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("ads", res);
        responseData.put("user", user);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(responseData);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("FINISH SearchFilter SERVLET");
    }
}
