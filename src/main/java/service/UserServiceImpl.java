package service;

import dao.UserDao;
import dao.UserDaoJDBCImpl;
import model.User;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
        userDao.createUsersTable();
    }
    public void dropUsersTable() throws SQLException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);
    }


    public List<User> getAllUsers() throws SQLException {
        for (User allUser : userDao.getAllUsers()) {
            System.out.println(allUser);
        }
        return userDao.getAllUsers();

    }


    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();
    }

}
