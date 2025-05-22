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
import org.example.mvc.dao.UplataDAO;
import org.example.mvc.model.Psihoterapeut;
import org.example.mvc.model.Uplata;

import java.math.BigDecimal;
import java.util.List;

public class PaymentOverviewForm {
    private final Stage stage;
    private final Psihoterapeut therapist;

    public PaymentOverviewForm(Stage stage, Psihoterapeut therapist) {
        this.stage = stage;
        this.therapist = therapist;
        initUI();
    }

    private void initUI() {
        TableView<Uplata> table = new TableView<>();
        TableColumn<Uplata, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Uplata, Integer> colClientId = new TableColumn<>("Klijent ID");
        colClientId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));
        TableColumn<Uplata, String> colSvrha = new TableColumn<>("Svrha");
        colSvrha.setCellValueFactory(new PropertyValueFactory<>("svrha"));
        TableColumn<Uplata, Integer> colRata = new TableColumn<>("Rata");
        colRata.setCellValueFactory(new PropertyValueFactory<>("rata"));
        TableColumn<Uplata, Integer> colValuta = new TableColumn<>("Valuta_id");
        colValuta.setCellValueFactory(new PropertyValueFactory<>("valuta_id"));
        TableColumn<Uplata, String> colNacin = new TableColumn<>("Način");
        colNacin.setCellValueFactory(new PropertyValueFactory<>("nacin"));
        TableColumn<Uplata, BigDecimal> colIznos = new TableColumn<>("Iznos");
        colIznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));
        TableColumn<Uplata, String> colDatum = new TableColumn<>("Datum");
        colDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        TableColumn<Uplata, Integer> colValutaId = new TableColumn<>("Valuta ID");
        colValutaId.setCellValueFactory(new PropertyValueFactory<>("valuta_id"));
        table.getColumns().addAll(colId, colClientId,colValutaId, colSvrha, colRata, colValuta, colNacin, colIznos, colDatum);

        try {
            List<Uplata> all = new UplataDAO().findAll();
            table.setItems(FXCollections.observableArrayList(all));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button btnBack = new Button("Nazad");
        btnBack.setOnAction(e -> new MainForm(stage, therapist));
        HBox hbox = new HBox(10, btnBack);
        hbox.setPadding(new Insets(10));

        BorderPane root = new BorderPane(table);
        root.setBottom(hbox);
        BorderPane.setMargin(table, new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Pregled uplata - " + therapist.getIme());
        stage.show();
    }
}

