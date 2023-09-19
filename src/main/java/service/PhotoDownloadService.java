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

public class PhotoDownloadService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START IMAGE SERVLET get");
        String name = req.getParameter("name");
        System.out.println(name);
        File downloadFile = null;
        for (File file : new File("c:\\cartrade_images\\").listFiles()) {
            if (name.equals(file.getName())) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader(
                "Content-Disposition", "attachment; filename=\""
                        + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
//        for (Photo photo : photos) {
//            String imagePath = photo.getPath();
//            File file = new File(imagePath);
//            FileInputStream fis = new FileInputStream(file);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = bis.read(buffer)) != -1) {
//                out.write(buffer, 0, bytesRead);
//            }
//            bis.close();
//            fis.close();
//        }
//        out.close();
        System.out.println("FINISH IMAGE SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}
