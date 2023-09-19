package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.Photo;
import repository.AdsRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class ImageService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START IMAGE SERVLET get");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        OutputStream out = resp.getOutputStream();
        var context = req.getServletContext();
        int id = Integer.parseInt(context.getAttribute("id").toString());
        Ads ads = AdsRepository.instOf().findAdsById(id);
        List<Photo> photos = ads.getPhotos();
        System.out.println(photos);
        for (Photo photo : photos) {
            String imagePath = photo.getPath();
            File file = new File(imagePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            bis.close();
            fis.close();
        }
        out.close();
        System.out.println("FINISH IMAGE SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}
