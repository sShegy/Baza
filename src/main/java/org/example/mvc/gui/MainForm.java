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
import org.example.mvc.dao.PsihoterapeutDAO;
import org.example.mvc.model.Psihoterapeut;

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
        // Tabela sa svim psihoterapeutima
        TableView<Psihoterapeut> table = new TableView<>();
        TableColumn<Psihoterapeut, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Psihoterapeut, String> colIme = new TableColumn<>("Ime");
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Psihoterapeut, String> colPrezime = new TableColumn<>("Prezime");
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Psihoterapeut, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        table.getColumns().addAll(colId, colIme, colPrezime, colEmail);

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

        btnProfile.setOnAction(e -> new TherapistProfileForm(stage, loggedIn));
        btnClients.setOnAction(e -> new ClientSignupsForm(stage, loggedIn));
        btnPastSessions.setOnAction(e -> new SessionHistoryForm(stage, loggedIn));
        btnUpcoming.setOnAction(e -> new UpcomingSessionsForm(stage, loggedIn));
        btnPayments.setOnAction(e -> new PaymentOverviewForm(stage, loggedIn));

        HBox hbox = new HBox(10, btnProfile, btnClients, btnPastSessions, btnUpcoming, btnPayments);
        hbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setTop(hbox);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Glavna forma - dobrodošli, " + loggedIn.getIme());
        stage.show();
    }
}
