package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.SearchFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchFilterService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START SearchFilter SERVLET");
        SearchFilter searchFilter = GSON.fromJson(req.getReader(), SearchFilter.class);
        System.out.println(searchFilter.toString());
        System.out.println("FINISH SearchFilter SERVLET");
    }
}
