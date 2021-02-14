package sample.dashboard;

import db_connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SaleController {

    private DBConnection dbConnection = null;
    private Connection connection = null;

    public boolean add(String cust_name, String cust_mobile, String cust_address, String product, String pquantity, String prate) {

        float quantity = Float.parseFloat(pquantity);
        float rate = Float.parseFloat(prate);

        try {

            DBConnection dbConnection = new DBConnection();
            // open db connection
            Connection connection = dbConnection.openConnection();

            String sql = "INSERT INTO sale(cust_name, cust_mobile, cust_address, product, quantity, rate, total) VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cust_name);
            statement.setString(2, cust_mobile);
            statement.setString(3, cust_address);
            statement.setString(4, product);
            statement.setFloat(5, quantity);
            statement.setFloat(6, rate);
            statement.setFloat(7, quantity * rate);

            int result = statement.executeUpdate();

            // close db connection
            dbConnection.closeConnection(connection);
            return Boolean.TRUE;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }


    public ArrayList<Sale> all() {

        ArrayList<Sale> salesdata;
        dbConnection = new DBConnection();

        try {
            // open db connection
            connection = dbConnection.openConnection();

            String sql = "SELECT * FROM sale";
            ResultSet result = connection.createStatement().executeQuery(sql);

            salesdata = new ArrayList();


            while (result.next()) {
                Sale sale = new Sale(
                        result.getInt("sale_id"),
                        result.getString("cust_name"),
                        result.getString("cust_mobile"),
                        result.getString("cust_address"),
                        result.getString("product"),
                        result.getFloat("quantity"),
                        result.getFloat("rate"),
                        result.getFloat("total")
                );


                salesdata.add(sale);
                System.out.println("Hello");
            }

            return salesdata;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                dbConnection.closeConnection(connection);
        }
        return null;



    }

    private static String[] push(String[] array, String push) {
        String[] longer = new String[array.length + 1];
        for (int i = 0; i < array.length; i++)
            longer[i] = array[i];
        longer[array.length] = push;
        return longer;
    }


    public String[] productList() {
        String [] data;
        try {
            // open db connection
            connection = dbConnection.openConnection();

            String sql = "SELECT * FROM product";
            ResultSet result = connection.createStatement().executeQuery(sql);
            data = new String[-1];
            while (result.next()) {
                Product product = new Product(
                        result.getInt("pro_id"),
                        result.getString("name"),
                        result.getString("unit"),
                        result.getFloat("quantity"),
                        result.getFloat("rate"),
                        result.getFloat("total")
                );

                String name = product.getName();
                data = push(data, name);
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                dbConnection.closeConnection(connection);
        }

        return null;
    }

}
