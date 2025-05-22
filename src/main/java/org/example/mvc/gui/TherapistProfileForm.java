package org.example.mvc.gui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

import java.sql.SQLException;

public class TherapistProfileForm {
    private final Stage stage;
    private final Psihoterapeut therapist;

    public TherapistProfileForm(Stage stage, Psihoterapeut therapist) {
        this.stage = stage;
        this.therapist = therapist;
        initUI();
    }

    private void initUI() {
        Psihoterapeut fresh;
        try {
            fresh = new PsihoterapeutDAO().findById(therapist.getId());
            if (fresh == null) {
                new Alert(Alert.AlertType.ERROR, "Terapеut nije pronađen u bazi").showAndWait();
                return;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Greška pri učitavanju podataka").showAndWait();
            return;
        }
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTitle = new Label("Profil psihoterapeuta");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        grid.add(lblTitle, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        grid.add(new Label(String.valueOf(fresh.getId())), 1, 1);

        grid.add(new Label("Ime:"), 0, 2);
        grid.add(new Label(fresh.getIme()), 1, 2);

        grid.add(new Label("Prezime:"), 0, 3);
        grid.add(new Label(fresh.getPrezime()), 1, 3);

        grid.add(new Label("JMBG:"), 0, 4);
        grid.add(new Label(fresh.getJmbg()), 1, 4);

        grid.add(new Label("Datum rođenja:"), 0, 5);
        grid.add(new Label(fresh.getDatumRodjenja().toString()), 1, 5);

        grid.add(new Label("Telefon:"), 0, 6);
        grid.add(new Label(fresh.getTelefon()), 1, 6);

        grid.add(new Label("Email:"), 0, 7);
        grid.add(new Label(fresh.getEmail()), 1, 7);

        grid.add(new Label("Oblast:"), 0, 8);
        grid.add(new Label(fresh.getOblastNaziv()), 1, 8);

        grid.add(new Label("Datum sertifikacije:"), 0, 9);
        grid.add(new Label(fresh.getDatumSertifikacije().toString()), 1, 9);

        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new MainForm(stage, fresh));
        grid.add(btnBack, 0, 10);

        Scene scene = new Scene(grid, 450, 520);
        stage.setScene(scene);
        stage.setTitle("Moj profil - " + fresh.getIme());
        stage.show();
    }
}

