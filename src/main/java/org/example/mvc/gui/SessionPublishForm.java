package org.example.mvc.gui;



import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.mvc.dao.ObjavaPodatakaDAO;
import org.example.mvc.model.ObjavaPodataka;
import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.model.Seansa;

import java.time.LocalDate;

public class SessionPublishForm {
    private final Stage stage;
    private final Psihoterapeut therapist;
    private final Seansa seansa;

    public SessionPublishForm(Stage stage, Psihoterapeut therapist, Seansa seansa) {
        this.stage = stage;
        this.therapist = therapist;
        this.seansa = seansa;
        initUI();
    }

    private void initUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTitle = new Label("Objava podataka za seansu");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        grid.add(lblTitle, 0, 0, 2, 1);

        grid.add(new Label("Seansa ID:"), 0, 1);
        grid.add(new Label(String.valueOf(seansa.getId())), 1, 1);

        grid.add(new Label("Primalac (npr. policija, sud):"), 0, 2);
        TextField tfPrimalac = new TextField();
        grid.add(tfPrimalac, 1, 2);

        grid.add(new Label("Datum objave:"), 0, 3);
        DatePicker dpDatum = new DatePicker(LocalDate.now());
        grid.add(dpDatum, 1, 3);

        grid.add(new Label("Razlog:"), 0, 4);
        TextArea taRazlog = new TextArea();
        taRazlog.setPrefRowCount(4);
        grid.add(taRazlog, 1, 4);

        Button btnSave = new Button("Sačuvaj");
        Button btnBack = new Button("Nazad");
        HBox hbox = new HBox(10, btnSave, btnBack);
        hbox.setPadding(new Insets(10));
        grid.add(hbox, 1, 5);

        btnSave.setOnAction(e -> handleSave(
                tfPrimalac.getText().trim(),
                dpDatum.getValue(),
                taRazlog.getText().trim()
        ));
        btnBack.setOnAction(e -> new SessionDetailsForm(stage, therapist, seansa));

        Scene scene = new Scene(grid, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Objavi seansu - " + seansa.getDatum());
        stage.show();
    }

    private void handleSave(String primalac, LocalDate datum, String razlog) {
        try {
            // TODO: implement model.ObjavaPodataka and dao.ObjavaPodatakaDAO
            ObjavaPodataka objava = new ObjavaPodataka(seansa.getId(), primalac, datum, razlog);
            new ObjavaPodatakaDAO().save(objava);
            new Alert(Alert.AlertType.INFORMATION, "Objava uspešno sačuvana").showAndWait();
            new SessionDetailsForm(stage, therapist, seansa);
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Greška pri čuvanju objave").showAndWait();
        }
    }
}

