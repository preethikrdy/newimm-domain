package edu.gmu.cs321;

import java.util.UUID;

import com.cs321.Workflow;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class GreenCardUpdateController {

    @FXML
    private VBox formView;

    @FXML
    private VBox confirmationView;

    @FXML
    private Label loggedInLabel;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField birthdateField;

    @FXML
    private TextField ssNumberField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField zipField;

    @FXML
    private TextField stateField;

    @FXML
    private Button saveDraftButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button helpButton;

    @FXML
    public void initialize() {
        // Populate language options
        languageComboBox.getItems().addAll("ENGLISH", "ITALIAN");
        languageComboBox.setValue("ENGLISH");

        // Set logged-in user
        loggedInLabel.setText("Logged in: Luigi");
    }

    @FXML
    private void saveDraft() {
        System.out.println("Draft saved!");
    }

    @FXML
    private void submit() {
        try {
            // Build the address and immigrant
            Address address = new Address(
                streetField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipField.getText()
            );

            String fullName = firstNameField.getText() + " " + lastNameField.getText();

            Immigrant immigrant = new Immigrant(
                fullName,
                birthdateField.getText(),
                ssNumberField.getText(),
                "USA",
                "Pending",
                address
            );

            // Save to database
            int immigrantId = FormDAO.saveImmigrant(immigrant); // get auto-generated ID
            System.out.println("Saved immigrant with ID: " + immigrantId);

            // Add to workflow
            Workflow workflow = new Workflow();  // uses database.properties
            int result = workflow.AddWFItem(immigrantId, "Review");
            System.out.println("Workflow insertion result: " + result);
            workflow.closeConnection();

            formView.setVisible(false);
            confirmationView.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void showHelp() {
        System.out.println("Help clicked!");
    }
}