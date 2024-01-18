package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegService {

    private static final Gson GSON = new GsonBuilder().create();

    public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("START REG SERVLET get");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepository.instOf().findUserByEmail(req.getParameter("email"));
        if (user == null) {
            System.out.println("reg");
            user = User.of(name, email, password);
            UserRepository.instOf().save(user);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            System.out.println("error");
            req.setAttribute("error", "Email занят");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
        System.out.println("FINISH REG SERVLET get");
    }
}
