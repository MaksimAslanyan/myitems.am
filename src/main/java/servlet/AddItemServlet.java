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


    private static final String UPLOAD_DIR = "/Users/karen/data/lessons/java2021/web/UserBook/uploadedImages/";

    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager =  new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/add/item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        Part profilePicPart = req.getPart("profilePic");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        String fileName = null;
        if (profilePicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + profilePicPart.getName();
            profilePicPart.write(UPLOAD_DIR + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .picUrl(fileName)
                .categoryId(categoryManager.getById(categoryId))
                .userId(userManager.getById(userId))
                .build();

        itemManager.addItem(item);
        req.getSession().setAttribute("msg", "Item was added");
        resp.sendRedirect("/add/item");

    }


}
