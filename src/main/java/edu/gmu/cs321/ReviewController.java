package edu.gmu.cs321;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;;


public class ReviewController {
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

    @FXML
    public void initialize() {
        toggleEdit(false);
        ssnField.setText("444-92--7820");
        nameField.setText("Yoshi Tezuka");
        originField.setText("Japan");
        addressField.setText("1385 Mushroom Drive, Toad's Palace, CA 58147");
        dobField.setText("1990-12-04");
        userLabel.setText("UserID: bwser logged in");
        statusField.setText("In Review");
    }

    @FXML
    private void saveApplication(){
        // Placeholder... show some visual on the screen to show it saved.
        System.out.println("Application saved.");

        // Save into database... (something like UPDATE with SSN being the PK)
        toggleEdit(false); // Disable edit mode after hitting save
    }

    @FXML
    private void edit(){
        toggleEdit(true);
    }

    @FXML
    private void markForApproval(){
        statusField.setText("Waiting for approval...");
        toggleEdit(false);
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
