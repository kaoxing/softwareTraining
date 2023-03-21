package com.example.softwaretraining;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;

public class PlayController {

    @FXML
    private TextField timeField;

    @FXML
    private Label welcomeText;

    @FXML
    private Button playButton;

    @FXML
    void onPlayButton(ActionEvent event) {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeField.setText(sdf.format(System.currentTimeMillis()));
    }

}
