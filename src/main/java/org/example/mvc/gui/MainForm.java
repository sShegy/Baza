// src/main/java/org/example/mvc/gui/MainForm.java
package org.example.mvc.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

import java.time.LocalDate;
import java.util.List;

public class MainForm {
    private final Stage stage;
    private final Psihoterapeut loggedIn;

    public MainForm(Stage stage, Psihoterapeut loggedIn) {
        this.stage = stage;
        this.loggedIn = loggedIn;
        initUI();
    }
    private void initUI() {
        // Tabela sa svim kolona Psihoterapeuta
        TableView<Psihoterapeut> table = new TableView<>();

        TableColumn<Psihoterapeut, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Psihoterapeut, String> colIme = new TableColumn<>("Ime");
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Psihoterapeut, String> colPrezime = new TableColumn<>("Prezime");
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Psihoterapeut, String> colJmbg = new TableColumn<>("JMBG");
        colJmbg.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
        TableColumn<Psihoterapeut, LocalDate> colDatumRodjenja = new TableColumn<>("Datum Rođenja");
        colDatumRodjenja.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        TableColumn<Psihoterapeut, String> colPrebivaliste = new TableColumn<>("Prebivalište");
        colPrebivaliste.setCellValueFactory(new PropertyValueFactory<>("prebivaliste"));
        TableColumn<Psihoterapeut, String> colTelefon = new TableColumn<>("Telefon");
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        TableColumn<Psihoterapeut, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Psihoterapeut, String> colOblast = new TableColumn<>("Oblast");
        colOblast.setCellValueFactory(new PropertyValueFactory<>("oblastNaziv"));
        TableColumn<Psihoterapeut, LocalDate> colDatumSert = new TableColumn<>("Datum Sertifikacije");
        colDatumSert.setCellValueFactory(new PropertyValueFactory<>("datumSertifikacije"));

        table.getColumns().addAll(
                colId, colIme, colPrezime,
                colJmbg, colDatumRodjenja, colPrebivaliste,
                colTelefon, colEmail, colOblast, colDatumSert
        );

        // Učitavanje podataka
        try {
            List<Psihoterapeut> list = new PsihoterapeutDAO().findAll();
            table.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Dugmad za prelazak na ostale forme
        Button btnProfile = new Button("Moj profil");
        Button btnClients = new Button("Novi klijenti");
        Button btnPastSessions = new Button("Odradjene seanse");
        Button btnUpcoming = new Button("Buduce seanse");
        Button btnPayments = new Button("Uplate");

        btnProfile.setOnAction(e -> {
            Psihoterapeut sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) {
                new TherapistProfileForm(stage, sel);
            } else {
                new Alert(Alert.AlertType.WARNING, "Молим, изаберите терапеута из табеле")
                        .showAndWait();
            }
        });
        btnClients.setOnAction(e -> new ClientSignupsForm(stage, loggedIn));
        btnPastSessions.setOnAction(e -> new SessionHistoryForm(stage, loggedIn));
        btnUpcoming.setOnAction(e -> new UpcomingSessionsForm(stage, loggedIn));
        btnPayments.setOnAction(e -> new PaymentOverviewForm(stage, loggedIn));

        HBox hbox = new HBox(10, btnProfile, btnClients, btnPastSessions, btnUpcoming, btnPayments);
        hbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setTop(hbox);

        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Glavna forma - dobrodošli, " + loggedIn.getIme());
        stage.show();
    }
}
