package org.example.mvc.gui;



import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.mvc.dao.SeansaDAO;
import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.model.Seansa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class SessionHistoryForm {
    private final Stage stage;
    private final Psihoterapeut therapist;

    public SessionHistoryForm(Stage stage, Psihoterapeut therapist) {
        this.stage = stage;
        this.therapist = therapist;
        initUI();
    }

    private void initUI() {
        TableView<Seansa> table = new TableView<>();
        TableColumn<Seansa, LocalDate> colDatum = new TableColumn<>("Datum");
        colDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        TableColumn<Seansa, LocalTime> colVreme = new TableColumn<>("Vreme");
        colVreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));
        TableColumn<Seansa, Integer> colTrajanje = new TableColumn<>("Trajanje (min)");
        colTrajanje.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        TableColumn<Seansa, String>  colBeleske = new TableColumn<>("Beleške");
        colBeleske.setCellValueFactory(new PropertyValueFactory<>("beleske"));
        TableColumn<Seansa, Integer> colSeansaId = new TableColumn<>("Seansa ID");
        colSeansaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Seansa, Integer> colKlijentId = new TableColumn<>("Klijent ID");
        colKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));
        TableColumn<Seansa, Integer> colVodeci = new TableColumn<>("Vodeći korisnik");
        colVodeci.setCellValueFactory(new PropertyValueFactory<>("vodeci_korisnik"));
        TableColumn<Seansa, Integer> colSupervizija = new TableColumn<>("Pod supervizijom");
        colSupervizija.setCellValueFactory(new PropertyValueFactory<>("pod_supervizijom"));
        TableColumn<Seansa, Integer> colPsihoterapeut = new TableColumn<>("Psihoterapeut ID");
        colPsihoterapeut.setCellValueFactory(new PropertyValueFactory<>("psihoterapeutId"));

        TableColumn<Seansa,String> colKlijent = new TableColumn<>("Клијент");
        colKlijent.setCellValueFactory(new PropertyValueFactory<>("klijentFullName"));

        TableColumn<Seansa,String> colTerapeut = new TableColumn<>("Терапеут");
        colTerapeut.setCellValueFactory(new PropertyValueFactory<>("terapeutFullName"));

        table.getColumns().addAll(colSeansaId, colKlijent, colTerapeut, colVodeci, colSupervizija, colDatum, colVreme, colTrajanje, colBeleske);

        colSupervizija.setCellFactory(tc -> new TableCell<Seansa, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // ако је 1 -> "Да", иначе "Не"
                    setText(item == 1 ? "Да" : "Не");
                }
            }
        });

        try {
            List<Seansa> all = new SeansaDAO().findPastByTherapist(therapist.getId());
            List<Seansa> past = all;
            table.setItems(FXCollections.observableArrayList(past));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button btnBack = new Button("Nazad");
        Button btnDetails = new Button("Detalji");
        Button btnPublish = new Button("Објави сеансу");

        btnPublish.setOnAction(e -> {
            Seansa selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                new SessionPublishForm(stage, therapist, selected);
            }
        });
        btnBack.setOnAction(e -> new MainForm(stage, therapist));

        btnDetails.disableProperty().bind(
                table.getSelectionModel().selectedItemProperty().isNull()
        );

        btnDetails.setOnAction(e -> {
            Seansa selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                new SessionDetailsForm(stage, therapist, selected);
            }
        });

        HBox hbox = new HBox(10, btnDetails,btnPublish, btnBack);
        hbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(hbox);
        BorderPane.setMargin(table, new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Odradjene seanse - " + therapist.getIme());
        stage.show();
    }
}

