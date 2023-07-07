package dao;


import model.User;
import util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable()  {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS " +
                " mydbtest.users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ," +
                " name VARCHAR(255) ," +
                " lastName VARCHAR(255) NOT NULL," +
                " age INT NOT NULL ) ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate();

            System.out.println("Database has been created!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void dropUsersTable()  {
        String sqlCommand = " DROP TABLE IF EXISTS mydbtest.users ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.executeUpdate();

            System.out.println("Database has been deleted!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age)  {

        String sql = "insert into mydbtest.users (name,lastName,age) values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void removeUserById(long id)  {
        String sql = "delete from mydbtest.users where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }
    @Override
    public List<User> getAllUsers()  {
        List<User> userList = new ArrayList<>();

        String sql = "select id, name, lastName, age from mydbtest.users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                User user1 = new User();
                user1.setId(resultSet.getLong("id"));
                user1.setName(resultSet.getString("name"));
                user1.setLastName(resultSet.getString("lastName"));
                user1.setAge(resultSet.getByte("age"));

                userList.add(user1);
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return userList;
    }
    @Override
    public void cleanUsersTable()  {
        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE mydbtest.users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
