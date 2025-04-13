package edu.gmu.cs321;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ReviewerView extends Application{
    @Override
        public void start(Stage stage) {
            Label label = new Label("Reviewer View");

            VBox layout = new VBox(10);
            layout.getChildren().add(label);

            Scene scene = new Scene(layout, 400, 300);
            stage.setScene(scene);
            stage.setTitle("Reviewer Screen");
            stage.show();
        }
}
