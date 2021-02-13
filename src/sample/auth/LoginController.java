package sample.auth;

import db_connection.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import sample.Main;
import util.Core;
import util.Validation;


public class LoginController implements Initializable {

    public TextField tf_login_username;
    public PasswordField pf_login_password;
    public Button bt_login_login;
    public Hyperlink hyp_create_new_user;
    public Hyperlink hyp_login_help;
    public CheckBox cb_login_remember;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent actionEvent) {
        String email = tf_login_username.getText();
        String password = pf_login_password.getText();

        if (email.equals(" ") || password.equals(" ")) {
            Core.jfx_inv_alert(Alert.AlertType.WARNING, "Warning", "Username or password should not be empty!");
            return;
        } else {

            if (!Validation.email(email)) {
                Core.jfx_inv_alert(Alert.AlertType.WARNING, "Warning", "Please enter a valid email");
                return;
            }

            try {
                DBConnection dbConnection = new DBConnection();
                Connection connection = dbConnection.openConnection();

                String query = "SELECT * FROM tbl_user WHERE email=? AND password=?";

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, password);

                ResultSet result = statement.executeQuery();

                if (result == null || result.getFetchSize() < 0) {
                    Core.jfx_inv_alert(Alert.AlertType.ERROR, "Login Error!", "Email & Password did not match to our records.");
                } else {
                    Core core = new Core();
                    core.jfx_inv_screen("/sample/dashboard/dashboard.fxml", "Dashboard", 600, 420);
                }


            } catch (Exception e) {
                System.out.println(e);
            }

            Core.jfx_inv_alert(Alert.AlertType.INFORMATION, "Success", "Successfully your are logged in");
        }


    }


    public void create_new_user(ActionEvent actionEvent) {
        Core core = new Core();
        core.jfx_inv_screen("/sample/auth/registration.fxml", "Sign Up", 520, 530);
    }

    public void help_screen(ActionEvent actionEvent) {
        Core core = new Core();
        core.jfx_inv_screen("/sample/auth/help.fxml", "Help", 520, 530);
    }
}
