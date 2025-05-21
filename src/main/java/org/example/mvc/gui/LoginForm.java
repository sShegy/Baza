package org.example.mvc.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

import java.net.URL;

public class LoginForm {
    private final Stage stage;

    public LoginForm(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        // Root pane with gradient background (styled in CSS)
        StackPane root = new StackPane();
        root.getStyleClass().add("root-pane");

        // Card container in center
        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");

        // Load icon from classpath: src/main/resources/images/images.jpeg
        URL iconUrl = getClass().getResource("/images/images.jpeg");
        ImageView icon;
        if (iconUrl != null) {
            icon = new ImageView(new Image(iconUrl.toExternalForm()));
            icon.setFitWidth(80);
            icon.setPreserveRatio(true);
        } else {
            icon = new ImageView();
            System.err.println("Warning: /images/images.jpeg not found on classpath");
        }

        // Title label
        Label lblTitle = new Label("Novi Početak");
        lblTitle.getStyleClass().add("login-title");

        // Email field
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");
        tfEmail.getStyleClass().add("input-field");

        // Password field
        PasswordField pfPass = new PasswordField();
        pfPass.setPromptText("Lozinka");
        pfPass.getStyleClass().add("input-field");

        // Login button
        Button btnLogin = new Button("Prijava");
        btnLogin.getStyleClass().add("btn-primary");
        btnLogin.setOnAction(e -> handleLogin(tfEmail.getText().trim(), pfPass.getText().trim()));

        // Assemble card
        card.getChildren().addAll(icon, lblTitle, tfEmail, pfPass, btnLogin);
        root.getChildren().add(card);

        // Scene and stylesheet
        Scene scene = new Scene(root, 450, 350);
        scene.getStylesheets().add(getClass().getResource("/styles/login2.css").toExternalForm());

        // Show stage
        stage.setScene(scene);
        stage.setTitle("Savetovalište Novi početak – Prijava");
        stage.show();
    }

    private void handleLogin(String email, String password) {
        try {
            Psihoterapeut p = new PsihoterapeutDAO().findByEmailAndPassword(email, password);
            if (p != null) {
                new MainForm(stage, p);
            } else {
                new Alert(Alert.AlertType.ERROR, "Neispravni podaci za prijavu").showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Greška pri povezivanju sa bazom").showAndWait();
        }
    }
}
