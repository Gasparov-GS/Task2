package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws ClassNotFoundException {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Users (Id bigint PRIMARY KEY AUTO_INCREMENT, Name varchar(255), LastName varchar(255), Age INT)");
        }catch (SQLSyntaxErrorException sqlSyntaxErrorException){
            System.out.println("Table already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE Users");
        } catch (SQLSyntaxErrorException sqlSyntaxErrorException) {
            System.out.println("Table not exist");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO Users(Name,LastName,Age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
            System.out.println( "User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE Id = ? ")){
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> results= new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT  Name, LastName, Age FROM Users");
            while (rs.next()) {
                String name = rs.getString("Name");
                String lastName = rs.getString("LastName");
                Byte age = rs.getByte("Age");
                results.add(new User(name,lastName,age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void cleanUsersTable() {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Users")){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
