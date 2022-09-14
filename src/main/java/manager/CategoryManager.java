package manager;

import connectionpool.ConnectionPool;
import model.Category;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private ConnectionPool pool = new ConnectionPool();


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
        String sql = "select * from category where id = ?";
        try {
            Connection connection = pool.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
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

}

