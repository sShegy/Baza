package org.example.mvc.gui;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

public class LoginForm {
    private final Stage stage;

    public LoginForm(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblEmail = new Label("Email:");
        TextField tfEmail = new TextField();
        Label lblPass = new Label("Lozinka:");
        PasswordField pfPass = new PasswordField();
        Button btnLogin = new Button("Prijava");

        grid.add(lblEmail, 0, 0);
        grid.add(tfEmail, 1, 0);
        grid.add(lblPass, 0, 1);
        grid.add(pfPass, 1, 1);
        grid.add(btnLogin, 1, 2);

        btnLogin.setOnAction(e -> handleLogin(tfEmail.getText().trim(), pfPass.getText().trim()));

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Savetovalište Novi početak - Prijava");
        stage.show();
    }

    private void handleLogin(String email, String password) {
        try {
            Psihoterapeut p = new PsihoterapeutDAO().findByEmailAndPassword(email, password);
            if (p != null) {
                // Uspešna prijava, prikaz glavne forme
                new MainForm(stage,p);
            } else {
                new Alert(Alert.AlertType.ERROR, "Neispravni podaci za prijavu").showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Greška pri povezivanju sa bazom").showAndWait();
        }
    }
}

