import service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dmitriy", "Svechkolan", (byte) 28); // add sout with task in method
        userService.saveUser("Sergey", "Faevskiy", (byte) 27);
        userService.saveUser("Irina", "Faevskiy", (byte) 29);
        userService.saveUser("Julia", "Gnezdilova", (byte) 28);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
