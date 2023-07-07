package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import model.User;

import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


    public void createUsersTable() throws SQLException {
        userDaoHibernate.createUsersTable();
    }
    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);
    }


    public List<User> getAllUsers() throws SQLException {
        List<User> users =  userDaoHibernate.getAllUsers();
        for (User allUser : users) {
            System.out.println(allUser);
        }
        return users;

    }

    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }

}
