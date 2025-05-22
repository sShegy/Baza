package org.example.mvc.gui;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.mvc.dao.KlijentDAO;
import org.example.mvc.model.Klijent;
import org.example.mvc.model.Psihoterapeut;

import java.time.LocalDate;
import java.util.List;

public class ClientSignupsForm {
    private final Stage stage;
    private final Psihoterapeut therapist;

    public ClientSignupsForm(Stage stage, Psihoterapeut therapist) {
        this.stage = stage;
        this.therapist = therapist;
        initUI();
    }

    private void initUI() {
        TableView<Klijent> table = new TableView<>();
        TableColumn<Klijent, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Klijent, String> colIme = new TableColumn<>("Ime");
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Klijent, String> colPrezime = new TableColumn<>("Prezime");
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Klijent, String> colTelefon = new TableColumn<>("Telefon");
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<Klijent, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Klijent, String> colDatumRodjenja = new TableColumn<>("Datum rođenja");
        colDatumRodjenja.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        TableColumn<Klijent, Integer> colImaoPsihoterapiju = new TableColumn<>("Imao psihoterapiju");
        colImaoPsihoterapiju.setCellValueFactory(new PropertyValueFactory<>("imao_psihoterapiju"));
        TableColumn<Klijent, String> colOpisProblema = new TableColumn<>("Opis problema");
        colOpisProblema.setCellValueFactory(new PropertyValueFactory<>("opis_problema"));
        TableColumn<Klijent, String> colPol = new TableColumn<>("Pol");
        colPol.setCellValueFactory(new PropertyValueFactory<>("pol"));

        table.getColumns().addAll(colId, colIme, colPrezime,colDatumRodjenja,colPol, colTelefon, colEmail, colImaoPsihoterapiju, colOpisProblema);

        try {
            List<Klijent> list = new KlijentDAO().findAll();
            table.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new MainForm(stage, therapist));
        HBox hbox = new HBox(10, btnBack);
        hbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(hbox);
        BorderPane.setMargin(table, new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Prijave klijenata - " + therapist.getIme());
        stage.show();
    }
}
