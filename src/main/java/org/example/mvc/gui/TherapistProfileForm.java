package org.example.mvc.gui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.mvc.model.Psihoterapeut;

public class TherapistProfileForm {
    private final Stage stage;
    private final Psihoterapeut therapist;

    public TherapistProfileForm(Stage stage, Psihoterapeut therapist) {
        this.stage = stage;
        this.therapist = therapist;
        initUI();
    }

    private void initUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTitle = new Label("Profil psihoterapeuta");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        grid.add(lblTitle, 0, 0, 2, 1);

        grid.add(new Label("ID:"), 0, 1);
        grid.add(new Label(String.valueOf(therapist.getId())), 1, 1);
        grid.add(new Label("Ime:"), 0, 2);
        grid.add(new Label(therapist.getIme()), 1, 2);
        grid.add(new Label("Prezime:"), 0, 3);
        grid.add(new Label(therapist.getPrezime()), 1, 3);
        grid.add(new Label("Email:"), 0, 4);
        grid.add(new Label(therapist.getEmail()), 1, 4);

        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new MainForm(stage, therapist));
        grid.add(btnBack, 0, 5);

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Moj profil - " + therapist.getIme());
        stage.show();
    }
}

