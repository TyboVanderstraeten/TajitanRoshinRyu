/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beherenActiviteit;

import domein.Activiteit;
import domein.Lid;
import domein.controllers.ActiviteitBeheerController;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author robdeputter
 */
public class InschrijvingToevoegenScherm extends VBox {

    private final ActiviteitenBeherenScherm beherenActiviteitSchermController;
    private final ActiviteitBeheerController activiteitBeheerController;
    private final Activiteit activiteit;

    private TextField txtFamilienaam, txtVoornaam;

    private TableView<Lid> tblLeden;
    private TableColumn<Lid, String> colFamilienaam;
    private TableColumn<Lid, String> colVoornaam;
    private TableColumn<Lid, String> colGeboorteDatum;

    private Button btnVoegInschrijvingToe;
    private Button btnAnnuleer;
    private final Label lblError = new Label("");

    public InschrijvingToevoegenScherm(ActiviteitenBeherenScherm beherenActiviteitSchermController, ActiviteitBeheerController activiteitBeheerController, Activiteit activiteit) {
        this.beherenActiviteitSchermController = beherenActiviteitSchermController;
        this.activiteitBeheerController = activiteitBeheerController;
        this.activiteit = activiteit;
        buildGui();
    }

    private void buildGui() {
        maakFilters();
        maakTabel();
        maakButton();
        
        txtFamilienaam.setOnKeyReleased((KeyEvent event) -> {
            filter();
        });
        txtVoornaam.setOnKeyReleased((KeyEvent event) -> {
            filter();
        });
        
        btnVoegInschrijvingToe.setOnAction((ActionEvent event) -> {
            Lid lid = tblLeden.getSelectionModel().getSelectedItem();
            if (lid == null) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("FOUT!");
//                alert.setHeaderText("Ongekend lid");
//                alert.setContentText("U moet nog een lid selecteren!");
//                alert.showAndWait();
                  lblError.setText("U moet nog een lid selecteren!");
            }
            else{
                activiteitBeheerController.voegInschrijvingToe(activiteit, lid);
                Stage stage = (Stage) (getScene().getWindow());
                stage.close();
            }
        });
        
        btnAnnuleer.setOnAction((ActionEvent event) -> {
            Stage stage = (Stage) (getScene().getWindow());
            stage.close();
        });
    }

    public void maakFilters() {
        txtFamilienaam = new TextField();
        txtFamilienaam.setPromptText("Filter op familienaam");

        txtVoornaam = new TextField();
        txtVoornaam.setPromptText("Filter op voornaam");
        txtFamilienaam.setStyle("-fx-background-color: white");
        txtVoornaam.setStyle("-fx-background-color: white");


        HBox filters = new HBox(10);
        filters.getChildren().addAll(txtFamilienaam,txtVoornaam);
        filters.setPadding(new Insets(5,5,5,5));
        this.getChildren().add(filters);
    }

    public void maakTabel() {
        VBox tabel = new VBox();
        tabel.setPadding(new Insets(5,5,5,5));
        colFamilienaam = new TableColumn("Familienaam");
        colVoornaam = new TableColumn("Voornaam");
        colGeboorteDatum = new TableColumn("Geboortedatum");
        tblLeden = new TableView();
        
        DoubleBinding breedteScherm = this.widthProperty().multiply(0.97);
        DoubleBinding breedteKolom = breedteScherm.divide(3);

        tabel.prefWidthProperty().bind(breedteScherm);

        colFamilienaam.setCellValueFactory(cellData -> cellData.getValue().achternaamProperty());
        colFamilienaam.setResizable(false);
        colFamilienaam.setSortable(true);
        colFamilienaam.prefWidthProperty().bind(breedteKolom);
        colFamilienaam.getStyleClass().add("name-column");

        colVoornaam.setCellValueFactory(cellData -> cellData.getValue().voornaamProperty());
        colVoornaam.setResizable(false);
        colVoornaam.setSortable(true);
        colVoornaam.prefWidthProperty().bind(breedteKolom);
        colVoornaam.getStyleClass().add("name-column");
        
        colGeboorteDatum.setCellValueFactory(cellData -> cellData.getValue().geboortedatumProperty());
        colGeboorteDatum.setResizable(false);
        colGeboorteDatum.setSortable(true);
        colGeboorteDatum.prefWidthProperty().bind(breedteKolom);
        colGeboorteDatum.getStyleClass().add("name-column");

        tblLeden.setItems(activiteitBeheerController.geefLedenNogNietIngeschreven(activiteit));
        tblLeden.getColumns().addAll(colFamilienaam, colVoornaam, colGeboorteDatum);
        tblLeden.setPrefHeight(300);
        tblLeden.setScaleShape(false);
        tblLeden.setId("table");
        tblLeden.getSelectionModel().clearSelection();
        
        tblLeden.getStyleClass().add("titelLinks");
        tblLeden.getStyleClass().add("name-column");
        
        tblLeden.getStyleClass().add("selectionbar");
        
        lblError.setStyle("-fx-font-size: 15");
        lblError.setTextFill(Color.web("#B14643"));

        tabel.getChildren().add(tblLeden);
        tabel.getChildren().add(lblError);
        
        this.getChildren().add(tabel);
        //this.getChildren().add(lblError);

    }

    public void maakButton() {
        HBox box = new HBox();
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        
        
        btnVoegInschrijvingToe = new Button("Voeg lid toe");
        btnVoegInschrijvingToe.setStyle("-fx-font-size:15");
        btnAnnuleer = new Button("Annuleer");
        btnVoegInschrijvingToe.getStyleClass().add("crud");
        btnAnnuleer.getStyleClass().add("greyBtn");
        btnVoegInschrijvingToe.setPrefWidth(tblLeden.getPrefWidth());
        btnVoegInschrijvingToe.setMinWidth(100);
        btnAnnuleer.setPrefWidth(tblLeden.getPrefWidth());
        btnAnnuleer.setMinWidth(100);
        
        box.getChildren().addAll(btnAnnuleer,region,btnVoegInschrijvingToe);
        
        VBox button = new VBox(box);
        button.setPadding(new Insets(5));
        this.getChildren().add(button);
    }
    
    public void filter(){
        activiteitBeheerController.veranderFilterNogNietIngeschrevenLeden(txtFamilienaam.getText(), txtVoornaam.getText());
    }

}
