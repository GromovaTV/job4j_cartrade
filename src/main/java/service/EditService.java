package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.AdsDTO;
import repository.AdsRepository;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


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
        Object id = context.getAttribute("id");
        if (id != null) {
            // update ads
        } else {
            // save ads
        }
//        int id = Integer.parseInt(context.getAttribute("id").toString());
//        HbnStore.instOf().edit(id, item.getName(), item.getDescription(), item.isDone());
        System.out.println("FINISH SAVE");
    }
}