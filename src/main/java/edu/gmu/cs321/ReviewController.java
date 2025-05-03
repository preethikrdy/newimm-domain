package edu.gmu.cs321;

import com.cs321.Workflow;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ReviewController {

    @FXML private Label userLabel;
    @FXML private Button saveButton;
    @FXML private Button editButton;
    @FXML private Button markButton;
    @FXML private TextArea commentsBox;
    @FXML private VBox applicantBox;
    @FXML private TextField ssnField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextArea addressArea;
    @FXML private TextField dobField;
    @FXML private Button viewButton;
    @FXML private Button verifyButton;

    private int currentImmigrantId = -1;

    @FXML
    public void initialize() {
        loadNextForm();

        verifyButton.setOnAction(e -> {
            if (currentImmigrantId > 0) {
                try {
                    Workflow workflow = new Workflow();
                    int result = workflow.AddWFItem(currentImmigrantId, "Approve");
                    workflow.closeConnection();

                    if (result == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Immigrant has been sent to the Approver.");
                        alert.showAndWait();
                        clearFields();
                        loadNextForm();
                    } else {
                        showError("Failed to update workflow. Result code: " + result);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showError("An error occurred while sending to Approver.");
                }
            } else {
                showError("No immigrant loaded to verify.");
            }
        });
    }

    private void loadNextForm() {
        try {
            Workflow workflow = new Workflow();
            int nextId = workflow.GetNextWFItem("Review");

            if (nextId > 0) {
                Immigrant imm = FormDAO.getImmigrantById(nextId);  // Assumes FormDAO has this method
                currentImmigrantId = nextId;

                if (imm != null) {
                    ssnField.setText(imm.getSsn());

                    String[] names = imm.getFullName().split(" ", 2);
                    firstNameField.setText(names.length > 0 ? names[0] : "");
                    lastNameField.setText(names.length > 1 ? names[1] : "");

                    dobField.setText(imm.getDateOfBirth());
                    addressArea.setText(formatAddress(imm.getAddress()));
                } else {
                    showError("No immigrant found with ID: " + nextId);
                }
            } else {
                showInfo("No forms available for review.");
            }

            workflow.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error loading the next form.");
        }
    }

    private String formatAddress(Address addr) {
        return addr.getStreet() + "\n" + addr.getCity() + ", " + addr.getState() + " " + addr.getZip();
    }

    private void clearFields() {
        ssnField.clear();
        firstNameField.clear();
        lastNameField.clear();
        dobField.clear();
        addressArea.clear();
        currentImmigrantId = -1;
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
