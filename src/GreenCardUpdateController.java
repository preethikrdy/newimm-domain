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
        formView.setVisible(false);
        confirmationView.setVisible(true);
    }

    @FXML
    private void showHelp() {
        System.out.println("Help clicked!");
    }
}