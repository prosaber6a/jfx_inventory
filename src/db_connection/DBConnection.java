package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    String DB_URL = "jdbc:mysql://localhost:3306/jfx_inventory";
    String DB_USER = "root";
    String DB_PASSWORD = "saber2020";

    public Connection openConnection() {
        return getConnection();
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        }  catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}