package util;

import model.User;

import java.sql.*;

public class Util extends User {
    public static void main(String[] args) {
        Util util = new Util();
        util.getConnection();
    }

    static String url = "jdbc:mysql://localhost:3306/mydbtest";
    static String username = "root";
    static String password = "Roman22091995";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection completed!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }


}
