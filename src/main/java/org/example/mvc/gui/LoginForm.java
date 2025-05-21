// src/main/java/org/example/mvc/gui/LoginForm.java
package org.example.mvc.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

import java.net.URL;
import java.util.List;

public class LoginForm {
    private final Stage stage;

    public LoginForm(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        StackPane root = new StackPane();
        root.getStyleClass().add("root-pane");

        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("card");

        // Ikonica
        URL iconUrl = getClass().getResource("/images/images.jpeg");
        ImageView icon = iconUrl != null
                ? new ImageView(new Image(iconUrl.toExternalForm()))
                : new ImageView();
        icon.setFitWidth(80);
        icon.setPreserveRatio(true);

        Label lblTitle = new Label("Novi Početak");
        lblTitle.getStyleClass().add("login-title");

        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");
        tfEmail.getStyleClass().add("input-field");

        PasswordField pfPass = new PasswordField();
        pfPass.setPromptText("Lozinka");
        pfPass.getStyleClass().add("input-field");

        // Dugme za prijavu
        Button btnLogin = new Button("Prijava");
        btnLogin.getStyleClass().add("btn-primary");
        btnLogin.setOnAction(e -> handleLogin(tfEmail.getText().trim(), pfPass.getText().trim()));

        // Dugme za registraciju
        Button btnRegister = new Button("Registracija");
        btnRegister.getStyleClass().add("btn-secondary");
        btnRegister.setOnAction(e -> new RegistrationForm(stage));

        // Dugme za ulaz bez prijave
        Button btnSkip = new Button("Ulaz bez prijave");
        btnSkip.getStyleClass().add("btn-tertiary");
        btnSkip.setOnAction(e -> {
            try {
                List<Psihoterapeut> svi = new PsihoterapeutDAO().findAll();
                if (!svi.isEmpty()) {
                    new MainForm(stage, svi.get(0));
                } else {
                    new Alert(Alert.AlertType.WARNING, "Nema registrovanih terapeuta").showAndWait();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Greška pri učitavanju terapeuta").showAndWait();
            }
        });

        HBox buttons = new HBox(10, btnLogin, btnRegister, btnSkip);
        buttons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(icon, lblTitle, tfEmail, pfPass, buttons);
        root.getChildren().add(card);

        Scene scene = new Scene(root, 450, 350);
        scene.getStylesheets().add(getClass().getResource("/styles/login2.css").toExternalForm());

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
