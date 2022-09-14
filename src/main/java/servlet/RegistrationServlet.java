package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registr")
public class RegistrationServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registr.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        if (userManager.getUserByEmail(email) != null) {
            req.setAttribute("msg", "Author already exists");
            req.getRequestDispatcher("/").forward(req, resp);
        } else {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            String rePassword = req.getParameter("rePassword");
            if (!password.equals(rePassword)) {
                req.getSession().setAttribute("msg", "passwords are not match");
                resp.sendRedirect("/");
            } else {
                User user = User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .build();
                userManager.addUser(user);

                req.getSession().setAttribute("msg", "user was register successfully");
                resp.sendRedirect("/");
            }

        }
    }

}
