package manager;

import connectionpool.ConnectionPool;
import model.Category;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private ConnectionPool pool = new ConnectionPool();


    public void addCategory(Category category) {
        String sql = "insert into user (name) values (?)";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                category.setId(id);
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Category> getAllCategory() {
        String sql = "select * from category";
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(getCategoryFromResultSet(resultSet));
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getById(int id) {
        String sql = "select * from category where id = " + id;
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }

    public void edit(Category category) {
        String sql = "update category set name = ? where id = ?";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
            pool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(int id) {
        String sql = "delete from category where id = " + id;
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            pool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
