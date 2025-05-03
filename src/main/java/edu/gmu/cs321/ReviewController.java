package edu.gmu.cs321;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;


public class ReviewController {
    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

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
    private TextField nameField;

    @FXML 
    private TextField originField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField dobField;

    @FXML
    private Button viewButton;

    @FXML
    private Button verifyButton;
    
    @FXML
    private Button increaseButton;

    @FXML
    private Button decreaseButton;

    @FXML
    private TextField statusField;

    private double fontSize = 0;
    private boolean editMode = false;
    private ApplicationForm currApplication;

    @FXML
    // Dummy hard coded data
    public void initialize() {
        toggleEdit(editMode);

        currApplication = MockApplication.createSampleApp();
        populateFields(currApplication);

        userLabel.setText("UserID: bwser logged in");
        statusLabel.setText("VIEW MODE");
    }

    private void populateFields(ApplicationForm app){
        if(app == null || app.getImmigrant() == null) return;

        Immigrant imm = app.getImmigrant();
        Address addr = imm.getAddress();

        ssnField.setText(imm.getSsn());
        nameField.setText(imm.getFullName());
        dobField.setText(imm.getDateOfBirth());
        originField.setText(imm.getCountryOfOrigin());
        statusField.setText(imm.getImmigrationStatus());
        
        addressField.setText(addr.getStreet() + ", " + addr.getCity()
            + ", " + addr.getState() + " " + addr.getZip());
    }

    @FXML
    private void saveApplication(){
        // Placeholder... show some visual on the screen to show it saved.
        System.out.println("Application saved.");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Application saved successfully!");
        alert.showAndWait();

        // Save into database... (something like UPDATE with SSN being the PK)
        editMode = false;
        toggleEdit(editMode); // Disable edit mode after hitting save
    }

    @FXML
    private void edit(){
        editMode = !editMode;
        toggleEdit(editMode);
    }

    @FXML
    private void markForApproval(){
        statusField.setText("Waiting for approval...");
        editMode = false;
        toggleEdit(editMode);
    }

    @FXML 
    private void viewDocs(){
        // Fill with dummy data?
    }

    @FXML
    private void verify(){
        // Hard Code for presentation?

    }

    private void toggleEdit(boolean edit){
        ssnField.setEditable(edit);
        nameField.setEditable(edit);
        originField.setEditable(edit);
        addressField.setEditable(edit);
        dobField.setEditable(edit);
        statusField.setEditable(edit);

        String bgColor = edit ? "-fx-background-color:rgb(239, 245, 211)" : "-fx-background-color:rgb(240,240,240)";
        ssnField.setStyle(bgColor);
        nameField.setStyle(bgColor);
        originField.setStyle(bgColor);
        addressField.setStyle(bgColor);
        dobField.setStyle(bgColor);
        statusField.setStyle(bgColor);

        editButton.setText(edit ? "Quit" : "Edit");
        statusLabel.setText(edit ? "EDIT MODE": "VIEW MODE");
    }

    @FXML
    private void increaseFontSize(){
        changeFontSize(2);
    }

    @FXML
    private void decreaseFontSize(){
        changeFontSize(-2);
    }

    private void changeFontSize(double num){
        fontSize += num;
        Parent root = userLabel.getScene().getRoot();
        adjustFont(root, num);
    }
    
    private void adjustFont(Node node, double num){
        double currSize = -1;

        if (node instanceof Labeled) {
            Labeled labeled = (Labeled) node;
            currSize = labeled.getFont().getSize();
            labeled.setFont(Font.font(labeled.getFont().getFamily(), currSize + num));
        } else if (node instanceof TextField) {
            TextField textField = (TextField) node;
            currSize = textField.getFont().getSize();
            textField.setFont(Font.font(textField.getFont().getFamily(), currSize + num));
        } else if (node instanceof TextArea){ // Comments box
            TextArea textArea = (TextArea) node;
            currSize = textArea.getFont().getSize();
            textArea.setFont(Font.font(textArea.getFont().getFamily(), currSize + num));
        }

        // IF node is a parent apply to its children
        if (node instanceof Parent){
            for(Node child : ((Parent) node).getChildrenUnmodifiable()) {
                adjustFont(child, num);
            }
        }
    }
}
