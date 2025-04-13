package edu.gmu.cs321;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


public class JavaFX extends Application {

    @Override
    public void start(Stage stage) {
        //Creating buttons for each screen
        Button applicantBtn = new Button("Applicant View");
        Button reviewerBtn = new Button("Reviewer View");
        Button approverBtn = new Button("Approver View");

        //Setting the action for what each button should do
        applicantBtn.setOnAction(e -> new ApplicantView().start(new Stage()));
        reviewerBtn.setOnAction(e -> new ReviewerView().start(new Stage()));
        approverBtn.setOnAction(e -> new ApproverView().start(new Stage()));

        //Screen layout, and where the buttons should go
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