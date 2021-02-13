package sample.dashboard;

import db_connection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.Core;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    // Product input field
    public TextField tf_pro_name;
    public ComboBox cb_pro_unit;
    public TextField tf_pro_quantity;
    public TextField tf_pro_rate;
    public Button btn_pro_reset;
    public Button btn_pro_submit;
    public TableView product_table;
    public TableColumn pro_tbl_counter;
    public TableColumn pro_tbl_name;
    public TableColumn pro_tbl_unit;
    public TableColumn pro_tbl_qty;
    public TableColumn pro_tbl_rate;
    public TableColumn pro_tbl_total;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // product form unit cb
        String[] unit = {"KG", "Meter", "Ton"};
        cb_pro_unit.getItems().addAll(unit);
        get_all_product();
    }

    public void pro_form_reset(ActionEvent actionEvent) {
        clear_product_form();
    }


    public void clear_product_form() {
        tf_pro_name.setText(" ");
        cb_pro_unit.setValue(" ");
        tf_pro_quantity.setText(" ");
        tf_pro_rate.setText(" ");
    }

    public void get_all_product() {

        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        try {

            DBConnection dbConnection = new DBConnection();
            // open db connection
            Connection connection = dbConnection.openConnection();

            String sql = "SELECT * FROM tbl_product";
            ResultSet result = connection.createStatement().executeQuery(sql);




            int i = 1;

            while(result.next()){
                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();

//                Float qty = Float.parseFloat(0);
                Float qty = 0.0f;
                Float rate = 0.0f;
                Float total = 0.0f;
//                product_table.add

                /*
                for(int i=1 ; i<=result.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(result.getString(i));
                    if (i == 4) {
                        qty = result.getFloat(i);
                    }

                    if (i == 5) {
                        rate = result.getFloat(i);
                        total = qty * rate;
                        row.add(total.toString());
                    }
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

                */

            }

            //FINALLY ADDED TO TableView
            product_table.setItems(data);

            // close db connection
            dbConnection.closeConnection(connection);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pro_form_submit(ActionEvent actionEvent) {
        //Get Value
        String name = tf_pro_name.getText();
        SingleSelectionModel unit = cb_pro_unit.getSelectionModel();
        Float quantity = Float.parseFloat(tf_pro_quantity.getText());
        Float rate = Float.parseFloat(tf_pro_rate.getText());

        if (name.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Product Name field must not be empty");
            return;
        }

        if (unit.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Unit field must not be empty");
            return;
        }

        if (quantity < 1) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Quantity field must not be empty");
            return;
        }

        if (rate < 0) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Product Name field must not be empty");
            return;
        }


        try {

            DBConnection dbConnection = new DBConnection();
            // open db connection
            Connection connection = dbConnection.openConnection();

            String sql = "INSERT INTO tbl_product(name, quantity, rate, unit) VALUES(?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setFloat(2, quantity);
            statement.setFloat(3, rate);
            statement.setString(4, (String) unit.getSelectedItem());

            int result = statement.executeUpdate();

            if (result > 0) {
                Core.jfx_inv_alert(Alert.AlertType.CONFIRMATION, "Success", "Successfully product inserted..!");
                clear_product_form();
            } else {
                Core.jfx_inv_alert(Alert.AlertType.ERROR, "Error", "Error while saving product..");
            }

            // close db connection
            dbConnection.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
