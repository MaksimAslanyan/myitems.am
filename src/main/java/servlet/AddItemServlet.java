package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add/item")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddItemServlet extends HttpServlet {


    private static final String UPLOAD_DIR = "C:\\Users\\PC user\\Desktop\\temp";

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Category> categoryList = categoryManager.getAllCategory();
        req.setAttribute("cat", categoryList);
        List<Item> myItem = itemManager.getMyItemById(user.getId());
        req.setAttribute("user", user);
        req.getRequestDispatcher("/additem.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Part itemPic = req.getPart("itemPic");
        String filename = null;
        if (itemPic != null) {
            long nanoTime = System.nanoTime();

            filename = nanoTime + "-" + itemPic.getSubmittedFileName();
            itemPic.write(UPLOAD_DIR + filename);
        }

        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(filename)
                .user(user)
                .build();
        itemManager.addItem(item);
        req.getSession().setAttribute("msg", "Item was added");
        resp.sendRedirect("/add/item");

    }


}
