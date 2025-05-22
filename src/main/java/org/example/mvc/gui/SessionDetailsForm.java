package org.example.mvc.gui;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.model.Seansa;

import java.util.List;

public class SessionDetailsForm {
    private final Stage stage;
    private final Psihoterapeut therapist;
    private final Seansa seansa;

    public SessionDetailsForm(Stage stage, Psihoterapeut therapist, Seansa seansa) {
        this.stage = stage;
        this.therapist = therapist;
        this.seansa = seansa;
        initUI();
    }

    private void initUI() {
        GridPane info = new GridPane();
        info.setPadding(new Insets(10));
        info.setHgap(10);
        info.setVgap(10);

        Label lblTitle = new Label("Detalji seanse");
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        info.add(lblTitle, 0, 0, 2, 1);

        info.add(new Label("Seansa ID:"),            0, 1);
        info.add(new Label(String.valueOf(seansa.getId())), 1, 1);

        info.add(new Label("Клијент:"),               0, 2);
        info.add(new Label(seansa.getKlijentFullName()), 1, 2);

        info.add(new Label("Терапеут:"),              0, 3);
        info.add(new Label(seansa.getTerapeutFullName()), 1, 3);

        info.add(new Label("Vodeći korisnik:"),       0, 4);
        info.add(new Label(String.valueOf(seansa.getVodeci_korisnik())), 1, 4);

        info.add(new Label("Pod supervizijom:"),     0, 5);
        info.add(new Label(seansa.getPod_supervizijom() == 1 ? "Да" : "Не"), 1, 5);

        info.add(new Label("Datum:"),                 0, 6);
        info.add(new Label(seansa.getDatum().toString()), 1, 6);

        info.add(new Label("Vreme:"),                 0, 7);
        info.add(new Label(seansa.getVreme().toString()), 1, 7);

        info.add(new Label("Trajanje (min):"),         0, 8);
        info.add(new Label(String.valueOf(seansa.getTrajanje())), 1, 8);

        info.add(new Label("Beleške:"),               0, 9);
        TextArea taNotes = new TextArea(seansa.getBeleske());
        taNotes.setEditable(false);
        taNotes.setWrapText(true);
        taNotes.setPrefRowCount(3);
        info.add(taNotes,                             1, 9);

        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new SessionHistoryForm(stage, therapist));
        HBox controls = new HBox(10, btnBack);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(info);
        root.setBottom(controls);

        Scene scene = new Scene(root, 500, 450);
        stage.setScene(scene);
        stage.setTitle("Detalji seanse - " + seansa.getDatum());
        stage.show();
    }
}

