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
        String sql = "insert into item (title,price,pic_url,category_id,user_id,) VALUES (?,?,?,?,?)";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getPicUrl());
            ps.setObject(4, item.getCategoryId());
            ps.setObject(5, item.getUserId());
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

    public void join(int eventId, int userId) {
        String sql = "insert into user_event(user_id,event_id) VALUES (?,?)";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, eventId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Item> getAll() {
        String sql = "select * from item";
        List<Item> events = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                events.add(getItemFromResultSet(resultSet));
            }
            pool.returnConnection(connection);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public Item getById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
        String sql = "delete from event where id = " + id;
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Item item = new Item();
        item.setId(resultSet.getInt(1));
        item.setTitle(resultSet.getString(2));
        item.setPrice(resultSet.getDouble(3));
        item.setPrice(resultSet.getDouble(4));
        int categoryId = resultSet.getInt("category_id");
        int userId = resultSet.getInt("user_id");
        Category category = categoryManager.getById(categoryId);
        category.setId(categoryId);
        User user = userManager.getById(userId);
        user.setId(userId);

        return item;
    }


    public void cancel(int eventId, int userId) {
        String sql = "delete from user_event where event_id = ? AND user_id = ?";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getEventsByUserId(int userId) {
        String userEventSql = "Select event_id from user_event where user_id = ?";
        PreparedStatement ps = null;
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            ps = connection.prepareStatement(userEventSql);
            ps.setInt(1, userId);
            ResultSet eventsResultSet = ps.executeQuery();
            while (eventsResultSet.next()) {
                items.add(getById(eventsResultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> search(String keyword) {
        String sql = "select * from event where name like '%" + keyword + "%' OR place like '%" + keyword + "%'";
        List<Item> events = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                events.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Item> filter(double minPrice, double maxPrice) {
        String sql = "select * from item where price > " + minPrice + " and price < " + maxPrice;
        List<Item> item = new ArrayList<>();
        try {

            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                item.add(getItemFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return item;

    }
}

