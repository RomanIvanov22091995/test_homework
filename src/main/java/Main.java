import dao.UserDao;
import service.UserService;
import service.UserServiceImpl;
import util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        UserService userService = new UserServiceImpl();
//
        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();






    }
}
