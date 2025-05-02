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
        // Gornji deo: detalji seanse
        GridPane info = new GridPane();
        info.setPadding(new Insets(10));
        info.setHgap(10);
        info.setVgap(10);
        info.add(new Label("Datum:"), 0, 0);
        info.add(new Label(seansa.getDatum().toString()), 1, 0);
        info.add(new Label("Vreme:"), 0, 1);
        info.add(new Label(seansa.getVreme().toString()), 1, 1);
        info.add(new Label("Trajanje:"), 0, 2);
        info.add(new Label(seansa.getTrajanje() + " min"), 1, 2);

        // Beleške
        Label lblNotes = new Label("Beleške:");
        TextArea taNotes = new TextArea(seansa.getBeleške());
        taNotes.setEditable(false);
        taNotes.setWrapText(true);
        taNotes.setPrefRowCount(5);

        // Testovi
        TableView<Object> tblTests = new TableView<>();
        // TODO: Definiši model klase za testove (PsiholoskiTest, RezultatTesta)
        // Ovde bi se dodale kolone i podaci
        tblTests.setPlaceholder(new Label("Nema podataka o testovima"));

        // Publikacije
        TableView<Object> tblPublikacije = new TableView<>();
        // TODO: Definiši model klase za objave (kome, kada, razlog)
        tblPublikacije.setPlaceholder(new Label("Nema podataka o objavama"));

        // Nazad dugme
        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new SessionHistoryForm(stage, therapist));
        HBox controls = new HBox(10, btnBack);
        controls.setPadding(new Insets(10));

        // Kompleksni raspored
        BorderPane root = new BorderPane();
        root.setTop(info);
        BorderPane.setMargin(info, new Insets(10));
        root.setCenter(taNotes);
        BorderPane.setMargin(taNotes, new Insets(10));

        // Donja polovina: split između testova i publikacija
        TabPane tabs = new TabPane();
        Tab tabTests = new Tab("Testovi", tblTests);
        Tab tabPubl = new Tab("Objave", tblPublikacije);
        tabs.getTabs().addAll(tabTests, tabPubl);
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        root.setBottom(tabs);
        root.setPrefSize(800, 600);

        // Glavna scena
        BorderPane main = new BorderPane();
        main.setCenter(root);
        main.setBottom(controls);

        Scene scene = new Scene(main);
        stage.setScene(scene);
        stage.setTitle("Detalji seanse - " + seansa.getDatum());
        stage.show();
    }
}

