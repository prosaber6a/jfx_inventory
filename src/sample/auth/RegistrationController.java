package sample.auth;

import db_connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.Core;
import util.Validation;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    public Hyperlink hyp_login;
    public TextField tf_first_name;
    public TextField tf_last_name;
    public TextField tf_email;
    public PasswordField pf_password;
    public PasswordField pf_re_password;
    public TextField tf_phone;
    public TextField tf_area_code;
    public TextArea ta_address;
    public DatePicker dp_dob;
    public ComboBox cb_gender;
    public Button btn_sign_up;
    public CheckBox chk_termcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] gender = {"Select gender", "Male", "Female", "Others"};
        cb_gender.getItems().addAll(gender);
    }

    public void go_login(ActionEvent actionEvent) {
        Core core = new Core();
        core.jfx_inv_screen("/sample/auth/login.fxml", "Login", 600, 420);
    }

    public void submit_signup(ActionEvent actionEvent) {
        String first_name = tf_first_name.getText();
        String last_name = tf_last_name.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();
        String re_password = pf_re_password.getText();
        String area_code = tf_area_code.getText();
        String phone = tf_phone.getText();
        SingleSelectionModel gender = cb_gender.getSelectionModel();
        String address = ta_address.getText();
        LocalDate dob = dp_dob.getValue();


        if (first_name.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "First name field must not be empty");
            return;
        }

        if (last_name.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Last name field must not be empty");
            return;
        }

        if (email.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Email field must not be empty");
            return;
        }
        if (!Validation.email(email)) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Please provide a valid email");
            return;
        }

        if (password.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Password field must not be empty");
            return;
        }

        if (re_password.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Re-type password field must not be empty");
            return;
        }

        if (area_code.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Area code field must not be empty");
            return;
        }

        if (phone.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Phone field must not be empty");
            return;
        }
        if (gender.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Please select you gender");
            return;
        }

        if (address.isEmpty()) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Address must not be empty");
            return;
        }


        if (!password.equals(re_password)) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Password not match to re-type password");
            return;
        }

        if (dp_dob.getValue() == null) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Error", "Date of Birth must not be empty");
            return;
        }

        try {

            DBConnection dbConnection = new DBConnection();
            // open db connection
            Connection connection = dbConnection.openConnection();

            String sql = "INSERT INTO user(first_name, last_name, email, password, area_code, phone, address,dob, gender) VALUES(?, ?, ?, ?, ?, ?, ?,?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, area_code);
            statement.setString(6, phone);
            statement.setString(7, address);
            statement.setDate(8, Date.valueOf(dob));
            statement.setString(9, (String) gender.getSelectedItem());



            int result = statement.executeUpdate();

            if (result > 0) {
                Core.jfx_inv_alert(Alert.AlertType.CONFIRMATION, "Success", "Successfully sign-up done!");
                clear_fields();
            } else {
                Core.jfx_inv_alert(Alert.AlertType.ERROR, "Error", "Error while submitting sign-up data");
            }

            // close db connection
            dbConnection.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void clear_fields() {
        tf_first_name.setText("");
        tf_last_name.setText("");
        tf_email.setText("");
        pf_password.setText("");
        pf_re_password.setText("");
        tf_phone.setText("");
        tf_area_code.setText("");
        ta_address.setText("");
        dp_dob.setValue(LocalDate.parse(" "));
        cb_gender.setValue(" ");


    }

    public void signup_btn_enable(ActionEvent actionEvent) {
        if (chk_termcon.isSelected()) {
            btn_sign_up.setDisable(false);
        } else {
            btn_sign_up.setDisable(true);
        }
    }
}
