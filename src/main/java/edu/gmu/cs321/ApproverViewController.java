package edu.gmu.cs321;

import com.cs321.Workflow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Comparator;

public class ApproverViewController {

    @FXML private TableView<FormRow> appTable;
    @FXML private TableColumn<FormRow, String> nameCol;
    @FXML private TableColumn<FormRow, String> statusCol;
    @FXML private Button viewBtn;
    @FXML private ComboBox<String> sortBox;

    private final ObservableList<FormRow> data = FXCollections.observableArrayList();

    public static class FormRow {
        private final ApplicationForm form;
        private final SimpleStringProperty name;
        private final SimpleStringProperty status;

        public FormRow(ApplicationForm form) {
            this.form = form;
            this.name = new SimpleStringProperty(form.getImmigrant().getFullName());
            this.status = new SimpleStringProperty(form.getImmigrant().getImmigrationStatus());
        }

        public String getName() { return name.get(); }
        public String getStatus() { return status.get(); }
        public ApplicationForm getForm() { return form; }
    }

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cell -> cell.getValue().name);
        statusCol.setCellValueFactory(cell -> cell.getValue().status);
        appTable.setItems(data);

        sortBox.getItems().addAll("Sort A–Z", "Sort Z–A");
        sortBox.setValue("Sort A–Z");

        sortBox.setOnAction(e -> {
            Comparator<FormRow> comp = Comparator.comparing(FormRow::getName);
            if ("Sort Z–A".equals(sortBox.getValue())) {
                comp = comp.reversed();
            }
            FXCollections.sort(data, comp);
        });

        loadData();
        viewBtn.setOnAction(e -> showFormDetails());
    }

    private void loadData() {
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immigrants");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Address address = new Address(
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip")
                );

                Immigrant immigrant = new Immigrant(
                        rs.getString("full_name"),
                        rs.getString("dob"),
                        rs.getString("ssn"),
                        rs.getString("country_of_origin"),
                        rs.getString("immigration_status"),
                        address
                );

                ApplicationForm form = new ApplicationForm(
                        "APP-" + rs.getRow(),
                        immigrant,
                        null,
                        100.0
                );

                data.add(new FormRow(form));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFormDetails() {
        FormRow selected = appTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an application.").showAndWait();
            return;
        }

        Immigrant i = selected.getForm().getImmigrant();
        Address a = i.getAddress();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Application Details");

        String content = "Name: " + i.getFullName() +
                "\nDOB: " + i.getDateOfBirth() +
                "\nSSN: " + i.getSsn() +
                "\nStatus: " + i.getImmigrationStatus() +
                "\nAddress: " + a.getStreet() + ", " + a.getCity() + ", " + a.getState() + " " + a.getZip();

        dialog.setContentText(content);
        dialog.getDialogPane().getButtonTypes().addAll(
                new ButtonType("Approve", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("Reject", ButtonBar.ButtonData.NO),
                ButtonType.CLOSE
        );

        dialog.showAndWait().ifPresent(response -> {
            try {
                if (response.getText().equals("Approve")) {
                    i.updateStatus("Approved");
                    selected.status.set("Approved");

                    Workflow wf = new Workflow();
                    wf.AddWFItem(getFormId(selected.getForm()), "Approve");
                    wf.closeConnection();

                    // ✅ Show confirmation popup
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Email Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("A confirmation email has been sent to the immigrant.");
                    alert.showAndWait();

                } else if (response.getText().equals("Reject")) {
                    i.updateStatus("Rejected");
                    selected.status.set("Rejected");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private int getFormId(ApplicationForm form) {
        try {
            return Integer.parseInt(form.getFormId().replace("APP-", "").trim());
        } catch (Exception e) {
            return -1;
        }
    }
}



