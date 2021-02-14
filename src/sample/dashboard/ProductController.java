package sample.dashboard;

import db_connection.DBConnection;
import javafx.scene.control.Alert;
import util.Core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProductController {

    private DBConnection dbConnection = null;
    private Connection connection = null;

    public boolean add(String name, String unit, float quantity, float rate) {
        try {

            DBConnection dbConnection = new DBConnection();
            // open db connection
            Connection connection = dbConnection.openConnection();

            String sql = "INSERT INTO product(name, quantity, rate, unit, total) VALUES(?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setFloat(2, quantity);
            statement.setFloat(3, rate);
            statement.setString(4, unit);
            statement.setFloat(5, quantity * rate);

            int result = statement.executeUpdate();

            // close db connection
            dbConnection.closeConnection(connection);
            return Boolean.TRUE;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }


    public ArrayList<Product> all() {
        ArrayList<Product> products;
        dbConnection = new DBConnection();

        try {
            // open db connection
            connection = dbConnection.openConnection();

            String sql = "SELECT * FROM product";
            ResultSet result = connection.createStatement().executeQuery(sql);

            products = new ArrayList();


            while (result.next()) {
                Product product = new Product(
                        result.getInt("pro_id"),
                        result.getString("name"),
                        result.getString("unit"),
                        result.getFloat("quantity"),
                        result.getFloat("rate"),
                        result.getFloat("total")
                );


                products.add(product);
            }

            return products;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                dbConnection.closeConnection(connection);
        }
        return null;
    }




}
