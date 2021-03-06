package gui;

import domein.Admin;
import domein.controllers.AdminController;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.beans.PropertyChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginForm extends AnchorPane {

    @FXML
    private TextField txfUsername;
    @FXML
    private PasswordField txfPassword;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lblWachtwoordVergeten;

    private AdminController adminController;

    public LoginForm(AdminController adminController) {
        this.adminController = adminController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        btnCancel.getStyleClass().add("greyBtn");
        btnLogin.getStyleClass().add("crud");
        txfPassword.setStyle("-fx-background-color: white;");
        txfUsername.setStyle("-fx-background-color: white;");
        
    }

    @FXML
    private void annuleer(ActionEvent event) {
        Stage stage = (Stage) (getScene().getWindow());
        stage.close();
    }

    @FXML
    private void meldAan(ActionEvent event) {
        Admin admin = new Admin(txfUsername.getText(), txfPassword.getText());
        if (adminController.adminBestaat(admin)) {
            adminController.setAangemeldeAdmin(admin);
            Stage stage = (Stage) (getScene().getWindow());
            stage.close();
        } else {
            txfUsername.clear();
            txfPassword.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aanmeldfout");
            alert.setHeaderText("Aanmelden niet geslaagd");
            alert.setContentText("De inloggegevens waren incorrect.");
            alert.showAndWait();
        }
    }
}
