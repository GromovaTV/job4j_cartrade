package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AuthService {
    private static final Gson GSON = new GsonBuilder().create();

    public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("START AUTH SERVLET get");
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        System.out.println(user);
        String json = GSON.toJson(user);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("FINISH AUTH SERVLET get");
    }

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("START AUTH SERVLET post");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepository.instOf().findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("auth done");
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect("/cartrade/index.html");
        } else {
            System.out.println("Error");
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.html").forward(req, resp);
        }
        System.out.println("FINISH AUTH SERVLET post");
    }
}
