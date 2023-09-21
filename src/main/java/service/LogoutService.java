package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("START AUTH SERVLET get");
        HttpSession session = req.getSession();
        session.invalidate();
        req.getRequestDispatcher("login.html").forward(req, resp);
        System.out.println("FINISH AUTH SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }
}
