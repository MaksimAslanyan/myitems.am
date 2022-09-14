package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cat")
public class CategoryServlet extends HttpServlet {

    ItemManager itemManager = new ItemManager();
    CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Item> itemsByCategoryId = itemManager.getItemsByCategoryId(Integer.parseInt(id));
        List<Category> categoryList = categoryManager.getAllCategory();
        req.setAttribute("category", categoryList);
        req.setAttribute("item", itemsByCategoryId);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
