package dao;


import model.User;
import util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS " +
                " mydbtest.users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL ," +
                " name VARCHAR(255) ," +
                " lastName VARCHAR(255) NOT NULL," +
                " age INT NOT NULL ) ";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void dropUsersTable() throws SQLException {
        String sqlCommand = " DROP TABLE IF EXISTS mydbtest.users ";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Database has been deleted!");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "insert into mydbtest.users (name,lastName,age) values (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void removeUserById(long id) throws SQLException {
        String sql = "delete from mydbtest.users where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "select id, name, lastName, age from mydbtest.users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user1 = new User();
                user1.setId(resultSet.getLong("id"));
                user1.setName(resultSet.getString("name"));
                user1.setLastName(resultSet.getString("lastName"));
                user1.setAge(resultSet.getByte("age"));

                userList.add(user1);
            }
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
        }  finally {
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }



    }
}
