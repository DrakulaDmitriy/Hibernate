package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE USERS" +
                "(id BIGINT not NULL PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45) not NULL, " +
                "lastName VARCHAR(45) not NULL, " +
                "age TINYINT UNSIGNED not NULL)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(createTable)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(dropTable)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(saveUser)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM users WHERE id = (?)";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(removeUserById)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUsers = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = Util.getConnection().prepareStatement(getAllUsers);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4)
                );
                user.setId(resultSet.getLong(1));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE users";
        try (PreparedStatement statement = Util.getConnection().prepareStatement(cleanTable)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
