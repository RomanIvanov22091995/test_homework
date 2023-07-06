package util;

import model.User;

import java.sql.*;

public class Util extends User {
    public static void main(String[] args) {
        Util util = new Util();
        util.getConnection();
    }

    String url = "jdbc:mysql://localhost:3306/mydbtest";
    String username = "root";
    String password = "Roman22091995";


    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection completed!");
            connection.setAutoCommit(false);
            connection.commit();
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
