// src/main/java/org/example/mvc/gui/RegistrationForm.java
package org.example.mvc.gui;

import java.sql.Connection;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegistrationForm {
    private final Stage stage;

    public RegistrationForm(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Red 0
        grid.add(new Label("Ime:"),              0, 0);
        TextField tfIme = new TextField();
        grid.add(tfIme,                          1, 0);

        // Red 1
        grid.add(new Label("Prezime:"),          0, 1);
        TextField tfPrezime = new TextField();
        grid.add(tfPrezime,                      1, 1);

        // Red 2
        grid.add(new Label("JMBG:"),             0, 2);
        TextField tfJmbg = new TextField();
        grid.add(tfJmbg,                         1, 2);

        // Red 3
        grid.add(new Label("Datum rođenja:"),    0, 3);
        DatePicker dpRodjenje = new DatePicker();
        grid.add(dpRodjenje,                     1, 3);

        // Red 4
        grid.add(new Label("Prebivalište:"),     0, 4);
        TextField tfPrebivaliste = new TextField();
        grid.add(tfPrebivaliste,                 1, 4);

        // Red 5
        grid.add(new Label("Telefon:"),          0, 5);
        TextField tfTelefon = new TextField();
        grid.add(tfTelefon,                      1, 5);

        // Red 6
        grid.add(new Label("Email:"),            0, 6);
        TextField tfEmail = new TextField();
        grid.add(tfEmail,                        1, 6);

        // Red 7
        grid.add(new Label("Oblast ID:"),        0, 7);
        TextField tfOblast = new TextField();
        grid.add(tfOblast,                       1, 7);

        // Red 8
        grid.add(new Label("Datum sertifikacije:"), 0, 8);
        DatePicker dpSert = new DatePicker();
        grid.add(dpSert,                            1, 8);

        // Red 9
        grid.add(new Label("Lozinka:"),          0, 9);
        PasswordField pfLozinka = new PasswordField();
        grid.add(pfLozinka,                     1, 9);

        // Dugme
        Button btnRegister = new Button("Registruj se");
        grid.add(btnRegister,                    1, 10);

        btnRegister.setOnAction(e -> {
            // validacija
            if (tfIme.getText().trim().isEmpty() ||
                    tfPrezime.getText().trim().isEmpty() ||
                    tfJmbg.getText().trim().isEmpty() ||
                    dpRodjenje.getValue() == null ||
                    tfPrebivaliste.getText().trim().isEmpty() ||
                    tfTelefon.getText().trim().isEmpty() ||
                    tfEmail.getText().trim().isEmpty() ||
                    tfOblast.getText().trim().isEmpty() ||
                    dpSert.getValue() == null ||
                    pfLozinka.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Sva polja su obavezna").showAndWait();
                return;
            }

            try {
                Psihoterapeut p = new Psihoterapeut(
                        tfIme.getText().trim(),
                        tfPrezime.getText().trim(),
                        tfJmbg.getText().trim(),
                        dpRodjenje.getValue(),
                        tfPrebivaliste.getText().trim(),
                        tfTelefon.getText().trim(),
                        tfEmail.getText().trim(),
                        Integer.parseInt(tfOblast.getText().trim()),
                        dpSert.getValue(),
                        pfLozinka.getText().trim()   // sada ide u polje `password`
                );

                new PsihoterapeutDAO().save(p);
                new Alert(Alert.AlertType.INFORMATION, "Uspešno registrovan. ID: " + p.getId())
                        .showAndWait();

                // vrati na login
                new LoginForm(stage);

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Oblast ID mora biti ceo broj").showAndWait();
            } catch (DateTimeParseException ex) {
                new Alert(Alert.AlertType.WARNING, "Neispravan format datuma").showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Greška pri registraciji: " + ex.getMessage())
                        .showAndWait();
            }
        });

        stage.setScene(new Scene(grid, 450, 520));
        stage.setTitle("Registracija psihoterapeuta");
        stage.show();
    }
}
