package com.example.karty;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;




public class Menu extends Application{

    private Scene opcje;
    public void start(Stage STG){
        STG.setTitle("CARD_GAME v1.0");
        VBox root = new VBox();

        root.setAlignment(Pos.CENTER);
        BackgroundFill backfl_1 = new BackgroundFill(Color.BLACK,null,null);
        Background back_1 = new Background(backfl_1);
        root.setBackground(back_1);


        TextField text = new TextField();
        text.setMinSize(700,100);
        text.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; ");

        Button zat = new Button("Zatwierdź");
        zat.setMinSize(400,100);
        zat.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );

        zat.setOnMouseEntered(mouseEvent -> {
            zat.setStyle("-fx-border-color: LIME; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
        });

        zat.setOnMouseExited(mouseEvent -> {
            zat.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
        });

        zat.setOnAction(actionEvent -> {
            String nazwa = text.getText();
            if(!nazwa.isEmpty()){
                Scene menu = menu_gr(nazwa);
                STG.setScene(menu);
            }
        });



        root.getChildren().addAll(text,zat);
        Scene scena = new Scene(root,1280,720);

        STG.setScene(scena);
        STG.show();

    }

private Scene menu_gr(String nazwa){

    FlowPane root = new FlowPane(Orientation.VERTICAL,100,50);
    root.setAlignment(Pos.CENTER);

    BackgroundFill backfl_1 = new BackgroundFill(Color.BLACK,null,null);
    Background back_1 = new Background(backfl_1);
    root.setBackground(back_1);

    Scene scena = new Scene(root,1280,720);


    //STG.setScene(scena);

    //przycik start
    Button strt = new Button("Start");
    strt.setMinSize(400,100);
    strt.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );

    strt.setOnMouseEntered(mouseEvent -> {
        strt.setStyle("-fx-border-color: LIME; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });

    strt.setOnMouseExited(mouseEvent -> {
        strt.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });

    //przycik wyjscia
    Button wyj = new Button("Wyjście");
    wyj.setMinSize(400,100);
    wyj.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );

    wyj.setOnMouseEntered(mouseEvent -> {
        wyj.setStyle("-fx-border-color: LIME; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });

    wyj.setOnMouseExited(mouseEvent -> {
        wyj.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });

    wyj.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Platform.exit();
        }
    });
    //wyjście z apki z pomocą esc
    scena.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if(keyEvent.getCode()==KeyCode.ESCAPE){
                Platform.exit();
                }
        }
    });



    //przycik opcji
    Button opcje = new Button("Opcje");
    opcje.setMinSize(400,100);
    opcje.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );

    opcje.setOnMouseEntered(mouseEvent -> {
        opcje.setStyle("-fx-border-color: LIME; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });

    opcje.setOnMouseExited(mouseEvent -> {
        opcje.setStyle("-fx-border-color: DARKRED; -fx-border-width: 4px; -fx-font-size: 45px; -fx-background-color: BLACK; -fx-text-fill: WHITE; " );
    });




    root.getChildren().addAll(strt,opcje,wyj);
    //STG.show();

    return scena;

}

    public static void main(String[] args){
        System.out.println("Launch");

        launch();
    }

}
