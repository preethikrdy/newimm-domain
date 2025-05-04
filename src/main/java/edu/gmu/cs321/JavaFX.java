package edu.gmu.cs321;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class JavaFX extends Application {

    @Override
    public void start(Stage stage) {
        Button applicantBtn = new Button("Applicant View");
        Button reviewerBtn = new Button("Reviewer View");
        Button approverBtn = new Button("Approver View");

        //Setting the action for what each button should do
        applicantBtn.setOnAction(e -> {
            try {    
                new ApplicantView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        reviewerBtn.setOnAction(e -> {
            try {
                new ReviewerView().start(new Stage());
            } catch (Exception ex){
                ex.printStackTrace();

        applicantBtn.setOnAction(e -> {
            try {
                new ApplicantView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Failed to launch ApplicantView: " + ex.getMessage());
            }
        });

        reviewerBtn.setOnAction(e -> {
            try {
                new ReviewerView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Failed to launch ReviewerView: " + ex.getMessage());

            }
        });

        approverBtn.setOnAction(e -> {
            try {
                new ApproverView().start(new Stage());
            } catch (Exception ex){
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Failed to launch ApproverView: " + ex.getMessage());
            }
        });

        VBox screen = new VBox(10, applicantBtn, reviewerBtn, approverBtn);
        Scene scene = new Scene(screen, 400, 400);

        stage.setTitle("Green Card System: Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}