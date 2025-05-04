package edu.gmu.cs321;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;


public class ReviewController {
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
=======
    private TextField ssnField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextField dobField;

    @FXML
    private TextArea commentsBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button editButton;

    @FXML
    private Button markButton;


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


    @FXML
    public void initialize() {
        // Get the most recent application from ApplicationDataStore
        ApplicationDataStore dataStore = ApplicationDataStore.getInstance();
        var applications = dataStore.getApplications();
        if (applications.isEmpty()) {
            userLabel.setText("UserID: Unknown logged in");
            ssnField.setText("N/A");
            firstNameField.setText("N/A");
            lastNameField.setText("N/A");
            dobField.setText("N/A");
            addressField.setText("N/A");
            return;
        }

        // Use the most recent application
        ApplicationForm form = applications.get(applications.size() - 1);
        Immigrant immigrant = form.getImmigrant();
        Address address = immigrant.getAddress();

        // Split full name into first and last name
        String[] nameParts = immigrant.getFullName().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        userLabel.setText("UserID: Reviewer logged in");
        ssnField.setText(immigrant.getSsn().isEmpty() ? "N/A" : immigrant.getSsn());
        firstNameField.setText(firstName.isEmpty() ? "N/A" : firstName);
        lastNameField.setText(lastName.isEmpty() ? "N/A" : lastName);
        dobField.setText(immigrant.getDateOfBirth().isEmpty() ? "N/A" : immigrant.getDateOfBirth());

        String addressText = (address.getStreet().isEmpty() ? "" : address.getStreet() + "\n") +
                            (address.getCity().isEmpty() ? "" : address.getCity() + ", ") +
                            (address.getState().isEmpty() ? "" : address.getState() + " ") +
                            (address.getZip().isEmpty() ? "" : address.getZip());
        addressField.setText(addressText.isEmpty() ? "N/A" : addressText);

        // Update the "View Documents" button to show documents
        viewButton.setOnAction(e -> {
            var docs = form.getDocuments();
            if (docs.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "No documents attached.").showAndWait();
            } else {
                String docList = docs.stream()
                                    .map(Document::getName)
                                    .reduce((a, b) -> a + "\n" + b)
                                    .orElse("");
                new Alert(Alert.AlertType.INFORMATION, "Documents:\n" + docList).showAndWait();
            }
        });
    }

    @FXML
    private void save() {
        System.out.println("Save button clicked");
    }

    @FXML
    private void edit() {
        System.out.println("Edit button clicked");
        ssnField.setEditable(true);
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        addressField.setEditable(true);
        dobField.setEditable(true);
    }

    @FXML
    private void markForApproval() {
        // Update the status of the most recent application
        ApplicationDataStore dataStore = ApplicationDataStore.getInstance();
        var applications = dataStore.getApplications();
        if (!applications.isEmpty()) {
            ApplicationForm form = applications.get(applications.size() - 1);
            form.getImmigrant().updateStatus("Marked for Approval");
            System.out.println("Marked for Approval: " + form.getImmigrant().getFullName());
        }
    }

    @FXML
    private void viewDocuments() {
        // Handled in initialize via viewButton.setOnAction
    }

    @FXML
    private void verifyInformation() {
        System.out.println("Verify Information button clicked");
    }
}
