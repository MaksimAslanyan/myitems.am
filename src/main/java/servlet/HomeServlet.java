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

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {


    CategoryManager categoryManager = new CategoryManager();
    ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> all = itemManager.getAll();
        request.setAttribute("all", all);
        List<Category> categoryList = categoryManager.getAllCategory();
        request.setAttribute("category", categoryList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
