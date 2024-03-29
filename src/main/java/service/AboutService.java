package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.AdsDTO;
import model.Photo;
import model.User;
import repository.AdsRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AboutService {

    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START ABOUT SERVLET get");
        resp.setContentType("application/json; charset=utf-8");
        var context = req.getServletContext();
        var id = Integer.parseInt(context.getAttribute("id").toString());
        Ads ads = AdsRepository.instOf().findAdsById(id);
        System.out.println(ads);
        AdsDTO adsDTO = new AdsDTO();
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        if (user != null && user.getId() == ads.getUser().getId()) {
            adsDTO.setUser(true);
        }
        adsDTO.setBrand(ads.getCar().getBrand().getName());
        adsDTO.setBody(ads.getCar().getBody().getName());
        adsDTO.setPrice(ads.getPrice());
        adsDTO.setDescription(ads.getDescription());
        adsDTO.setSold(ads.isSold());
        adsDTO.setCreated(ads.getCreated());
        List<String> paths = new ArrayList<>();
        for (Photo p : ads.getPhotos()) {
            String pth =  p.getPath();
            System.out.println(pth);
            String[] pa = pth.split("\\\\");
            String path = pa[2];
            System.out.println(path);
            paths.add(path);
        }
        adsDTO.setPaths(paths);
        String json = GSON.toJson(adsDTO);
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("FINISH ABOUT SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}
