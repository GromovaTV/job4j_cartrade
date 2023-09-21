package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.Photo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import repository.AdsRepository;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class PhotoUploadService extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START PHOTO UPLOAD SERVLET get");
        System.out.println("FINISH PHOTO UPLOAD SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START PHOTO UPLOAD SERVLET post");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = req.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String id = servletContext.getAttribute("id").toString();
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("c:\\cartrade_images\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField() && item.getSize() != 0) {
                    String path = folder + File.separator + item.getName();
                    File file = new File(path);
                    Photo photo = Photo.of(path);
                    Ads ads = AdsRepository.instOf().findAdsById(Integer.parseInt(id));
                    System.out.println(ads.toString());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                        AdsRepository.instOf().savePhoto(photo);
                        ads.addPhoto(photo);
                        AdsRepository.instOf().saveOrUpdate(ads);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        System.out.println("FINISH PHOTO UPLOAD SERVLET post");
    }
}
