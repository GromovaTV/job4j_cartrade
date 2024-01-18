package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PhotoDownloadService {

    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START IMAGE SERVLET get");
        String name = req.getParameter("name");
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
        System.out.println("FINISH IMAGE SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}
