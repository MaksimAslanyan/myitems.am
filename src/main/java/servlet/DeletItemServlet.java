package servlet;

import manager.ItemManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete/item")
public class DeletItemServlet extends HttpServlet {

    private ItemManager itemManager = new ItemManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("id"));
        itemManager.removeItemById(itemId);
        req.getSession().setAttribute("msg", "Item was removed");
        resp.sendRedirect("/");
    }
}
