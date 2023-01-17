package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            connection.setAutoCommit(false);
//            var statement = getConnection().createStatement(); // вариант через var допустим??
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user(id INT AUTO_INCREMENT primary key NOT NULL, nameu VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age INTEGER NOT NULL)");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user(nameu, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate("DELETE FROM user WHERE id="+id);
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT * FROM user";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("nameu"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("TRUNCATE user");
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void UsersTable() {

    }
}
