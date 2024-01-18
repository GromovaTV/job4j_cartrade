package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Ads;
import model.User;
import repository.AdsRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexService {

    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START INDEX SERVLET get");
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        var context = req.getServletContext();
        context.setAttribute("id", null);
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        List<Ads> res = AdsRepository.instOf().findAllAds();
        System.out.println(res);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("ads", res);
        responseData.put("user", user);
        String json = GSON.toJson(responseData);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("FINISH INDEX SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START INDEX SERVLET post");
        String id = req.getParameter("id");
        System.out.println(id);
        var context = req.getServletContext();
        context.setAttribute("id", id);
        System.out.println("FINISH INDEX SERVLET post");
    }
}
