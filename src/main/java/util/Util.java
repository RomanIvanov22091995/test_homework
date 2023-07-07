package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class Util extends User {
    public static void main(String[] args) {
        Util util = new Util();
        util.getConnection();
    }
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/mydbtest";
    static String username = "root";
    static String password = "Roman22091995";


    private static SessionFactory sessionFactory =  null;

    public static SessionFactory getConnection() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", driver)
                        .setProperty("hibernate.connection.url", url)
                        .setProperty("hibernate.connection.username", username)
                        .setProperty("hibernate.connection.password", password)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }




//    public static Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Connection completed!");
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if(connection!=null){
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return connection;
//    }


}
