package controller;

import model.User;
import repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserRepository.instOf().findUserByEmail(req.getParameter("email"));
        if (user == null) {
            user = User.of(name, email, password);
            UserRepository.instOf().save(user);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            req.setAttribute("error", "Email занят");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
