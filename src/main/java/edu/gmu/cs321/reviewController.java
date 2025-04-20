package edu.gmu.cs321;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;;

public class reviewController {
    @FXML
    private Label userLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;
    
    @FXML
    private Button markButton;

    @FXML 
    private TextArea commentsBox;

    @FXML
    private VBox applicantBox;

    @FXML
    private TextField ssnField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField dobField;

    @FXML
    private Button viewButton;

    @FXML
    private Button verifyButton;
}
