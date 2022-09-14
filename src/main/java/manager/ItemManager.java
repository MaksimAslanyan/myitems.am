package manager;

import connectionpool.ConnectionPool;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private ConnectionPool pool = new ConnectionPool();
    private UserManager userManager = new UserManager();

    private CategoryManager categoryManager = new CategoryManager();

    public void addItem(Item item) {
        String sql = "insert into item (title,price,pic_url,category_id,user_id) VALUES (?,?,?,?,?)";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getPicUrl());
            ps.setObject(4, item.getCategory());
            ps.setObject(5, item.getUser());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemsByCategoryId(int userId) {
        String sql = "Select * from item where category_id = ?";
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet eventsResultSet = ps.executeQuery();
            while (eventsResultSet.next()) {
                items.add(getById(eventsResultSet.getInt(1)));
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAll() {
        String sql = "select * from item limit 20";
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                items.add(getItemFromResultSet(resultSet));
            }
            pool.returnConnection(connection);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item getById(int id) {
        String sql = "select * from item where id = ?";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
            pool.returnConnection(connection);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeItemById(int id) {
        String sql = "delete from event where id = ?";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setTitle(resultSet.getString("title"));
        item.setPrice(resultSet.getDouble("price"));
        int categoryId = resultSet.getInt("category_id");
        int userId = resultSet.getInt("user_id");
        Category category = categoryManager.getById(categoryId);
        item.setCategory(category);
        User user = userManager.getById(userId);
        item.setUser(user);

        return item;
    }

    public List<Item> getMyItemById(int id) {
        String sql = "Select * from item where user_id = ?";
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet eventsResultSet = ps.executeQuery();
            while (eventsResultSet.next()) {
                items.add(getById(eventsResultSet.getInt(1)));
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }
}

