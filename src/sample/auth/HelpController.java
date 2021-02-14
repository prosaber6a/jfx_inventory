package sample.auth;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import util.Core;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    public Hyperlink hyp_login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goLoginScene(ActionEvent actionEvent) {
        Core core = new Core();
        core.jfx_inv_screen("/sample/auth/login.fxml", "Login", 520, 400);
    }
}
