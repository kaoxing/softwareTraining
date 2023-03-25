package com.example.softwaretraining;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class MainApplication extends Application {
    MainController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainPanel.fxml"));
        controller=fxmlLoader.getController();

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
    public static void main(String[] args){
        launch(args);

    }

}
