package sample.dashboard;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import util.Core;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public int i = 0;


    private ProductController productController;
    private SaleController saleController;
    // Product input field
    public TextField tf_pro_name;
    public ComboBox cb_pro_unit;
    public TextField tf_pro_quantity;
    public TextField tf_pro_rate;
    public Button btn_pro_reset;
    public Button btn_pro_submit;


    public TableView<Product> product_table;
    public TableColumn<Product, Integer> pro_tbl_counter;
    public TableColumn<Product, String> pro_tbl_name;
    public TableColumn<Product, String> pro_tbl_unit;
    public TableColumn<Product, Float> pro_tbl_quantity;
    public TableColumn<Product, Float> pro_tbl_rate;
    public TableColumn<Product, Float> pro_tbl_total;

    // sale
    public ComboBox cb_sale_product;
    public Button btn_sale_reset;
    public Button btn_sale_save;
    public TextField tf_sale_quantity;
    public TextField tf_sale_rate;
    public TextField tf_sale_cust_name;
    public TextField tf_sale_cust_mobile;
    public TextArea tf_sale_cust_address;

    public TableView<Sale> sale_table;
    public TableColumn<Sale, Integer> sale_tbl_counter;
    public TableColumn<Sale, String> sale_tbl_cust_name;
    public TableColumn<Sale, String> sale_tbl_cust_mobile;
    public TableColumn<Sale, String> sale_tbl_cust_address;
    public TableColumn<Sale, String> sale_tbl_product;
    public TableColumn<Sale, Float> sale_tbl_qty;
    public TableColumn<Sale, Float> sale_tbl_rate;
    public TableColumn<Sale, Float> sale_tbl_total;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productController = new ProductController();
        saleController = new SaleController();
        // product form unit cb
        String[] unit = {"KG", "Meter", "Ton"};
        cb_pro_unit.getItems().addAll(unit);
        //sale form
        /*
        String[] productsList = saleController.productList();
        ObservableList<Sale> productsList = saleController.productList();


        cb_sale_product.setItems(productsList);

         */
        pro_tbl_counter.setCellValueFactory(new PropertyValueFactory<>("pro_id"));
        pro_tbl_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        pro_tbl_unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        pro_tbl_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        pro_tbl_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        pro_tbl_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        sale_tbl_counter.setCellValueFactory(new PropertyValueFactory<>("sale_id"));
        sale_tbl_cust_name.setCellValueFactory(new PropertyValueFactory<>("cust_name"));
        sale_tbl_cust_mobile.setCellValueFactory(new PropertyValueFactory<>("cust_mobile"));
        sale_tbl_cust_address.setCellValueFactory(new PropertyValueFactory<>("cust_address"));
        sale_tbl_product.setCellValueFactory(new PropertyValueFactory<>("product"));
        sale_tbl_qty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sale_tbl_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        sale_tbl_total.setCellValueFactory(new PropertyValueFactory<>("total"));


        get_all_product();
        get_all_sale();

    }

    private void get_all_sale() {
        sale_table.getItems().clear();
        ArrayList<Sale> sales = saleController.all();
        sale_table.getItems().addAll(sales);
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

        product_table.getItems().clear();

        ArrayList<Product> products = productController.all();
        product_table.getItems().addAll(products);
    }

    public void pro_form_submit(ActionEvent actionEvent) {
        //Get Value
        String name = tf_pro_name.getText();
        String unit = (String) cb_pro_unit.getSelectionModel().getSelectedItem();
        float quantity = Float.parseFloat(tf_pro_quantity.getText());
        float rate = Float.parseFloat(tf_pro_rate.getText());

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

        boolean result = productController.add(name, unit, quantity, rate);
        if (result == Boolean.TRUE) {
            Core.jfx_inv_alert(Alert.AlertType.CONFIRMATION, "Success", "Successfully product inserted..!");
            clear_product_form();
            get_all_product();
        } else {
            Core.jfx_inv_alert(Alert.AlertType.ERROR, "Error", "Error while saving product..");
        }

    }

    public void sale_form_reset(ActionEvent actionEvent) {
        clear_sale_form();
    }

    public void clear_sale_form() {
        cb_sale_product.setValue(" ");
        tf_sale_cust_address.setText(" ");
        tf_sale_rate.setText(" ");
        tf_sale_quantity.setText(" ");
        tf_sale_cust_name.setText(" ");
        tf_sale_cust_mobile.setText(" ");
    }

    public void sale_form_submit(ActionEvent actionEvent) {
        //Get Value
        String cust_name = tf_sale_cust_name.getText();
        String cust_mobile = tf_sale_cust_mobile.getText();
        String cust_address = tf_sale_cust_address.getText();
        String product = (String) cb_sale_product.getSelectionModel().getSelectedItem();

        /*float quantity = Float.parseFloat(tf_sale_quantity.getText());
        float rate = Float.parseFloat(tf_sale_rate.getText());*/
        String quantity = tf_sale_quantity.getText();
        String rate = tf_sale_rate.getText();

        if (cust_name.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Customer Name field must not be empty");
            return;
        }
        if (cust_mobile.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Customer Mobile field must not be empty");
            return;
        }
        if (cust_address.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Customer Address field must not be empty");
            return;
        }
        /*
        if (product.equals("")) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Unit field must not be empty");
            return;
        }*/
        if (quantity.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Quantity field must not be empty");
            return;
        }
        if (rate.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Product Name field must not be empty");
            return;
        }

        SaleController saleController = new SaleController();
        boolean result = saleController.add(cust_name, cust_mobile, cust_address, product, quantity, rate);
        if (result == Boolean.TRUE) {
            Core.jfx_inv_alert(Alert.AlertType.CONFIRMATION, "Success", "Successfully sale info inserted..!");
            clear_sale_form();
            get_all_sale();
        } else {
            Core.jfx_inv_alert(Alert.AlertType.ERROR, "Error", "Error while saving sale info..");
        }
    }
}
