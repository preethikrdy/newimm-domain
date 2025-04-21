package edu.gmu.cs321;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReviewerView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReviewUI.fxml"));
        BorderPane root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Review Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}