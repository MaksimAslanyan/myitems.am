package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

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
        req.getRequestDispatcher("/additem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        int userId = Integer.parseInt(req.getParameter("id"));

        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(filename)
                .user(userManager.getById(userId))
                .build();
        itemManager.addItem(item);
        req.getSession().setAttribute("msg", "Item was added");
        resp.sendRedirect("/add/item");

    }


}
