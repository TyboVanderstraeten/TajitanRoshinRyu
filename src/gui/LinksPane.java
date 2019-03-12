/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.controllers.AdminController;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author florianlanduyt
 */
public class LinksPane extends BorderPane implements PropertyChangeListener{

    //private String title;
    private MenuKnoppen knoppen;
    private final AdminController ac;
    private BeginSchermFlo parent;
    private boolean ingelogd;

    public LinksPane(MenuKnoppen knoppen, AdminController ac, BeginSchermFlo beginscherm, boolean ingelogd) {
        //this.title = title;
        this.ac = ac;
        this.knoppen = knoppen;
        this.parent = beginscherm;
        this.ingelogd = ingelogd;
        this.ac.addPropertyChangeListener(this);
        buildGui();
    }


    private void buildGui() {
        
        this.setCenter(knoppen);

        Hyperlink terug = new Hyperlink();
        terug.setText("Terug");
        this.setBottom(terug);

        terug.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                terugNaarHoofdMenu();
            }

        });

        //Opmaak
        this.setDisable(!ingelogd);
        this.getStyleClass().add("bgr");
        terug.setStyle("-fx-font-size: 15px");

    }

    public void terugNaarHoofdMenu() {
        parent.setLeft(null);
        parent.setLeft(new LinksPane(new HoofdmenuKnoppen(parent), ac, parent, ingelogd));
        parent.setMenuTitle("Hoofdmenu");
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        parent.setIngelogd(true);
        ingelogd = true;
        this.setDisable(!ingelogd);
    }
}