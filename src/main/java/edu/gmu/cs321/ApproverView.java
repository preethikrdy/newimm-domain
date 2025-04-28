package edu.gmu.cs321;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;

public class ApproverView extends Application {

    public static class FormRow {
        private final ApplicationForm form;
        private final SimpleStringProperty applicantName;
        private final SimpleStringProperty immigrationStatus;

        public FormRow(ApplicationForm form) {
            this.form = form;
            this.applicantName = new SimpleStringProperty(form.getImmigrant().getFullName());
            this.immigrationStatus = new SimpleStringProperty(form.getImmigrant().getImmigrationStatus());
        }

        public String getApplicantName() { return applicantName.get(); }
        public String getImmigrationStatus() { return immigrationStatus.get(); }
        public ApplicationForm getForm() { return form; }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label titleLabel = new Label("Approver View");

        ComboBox<String> sortBox = new ComboBox<>();
        sortBox.getItems().addAll("Sort A–Z", "Sort Z–A");
        sortBox.setValue("Sort A–Z");

        TableView<FormRow> appTable = new TableView<>();
        ObservableList<FormRow> data = FXCollections.observableArrayList();

        TableColumn<FormRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell -> cell.getValue().applicantName);
        TableColumn<FormRow, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cell -> cell.getValue().immigrationStatus);

        appTable.getColumns().addAll(nameCol, statusCol);
        appTable.setItems(data);

        Button viewBtn = new Button("View Details");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, sortBox, appTable, viewBtn);

        // Load data directly from database
        try (Connection conn = DBHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immigrants");
            ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                Address addr = new Address(
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
                        addr
                );

                ApplicationForm form = new ApplicationForm(
                        "APP-" + rs.getRow(),
                        immigrant,
                        List.of(),
                        100.0 // placeholder
                );

                data.add(new FormRow(form));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sortBox.setOnAction(e -> {
            Comparator<FormRow> comp = Comparator.comparing(FormRow::getApplicantName);
            if ("Sort Z–A".equals(sortBox.getValue())) comp = comp.reversed();
            FXCollections.sort(data, comp);
        });

        viewBtn.setOnAction(e -> {
            FormRow selected = appTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                ApplicationForm form = selected.getForm();
                Immigrant i = form.getImmigrant();
                Address a = i.getAddress();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Applicant Details");

                String content = "Name: " + i.getFullName() +
                        "\nDOB: " + i.getDateOfBirth() +
                        "\nSSN: " + i.getSsn() +
                        "\nCountry: " + i.getCountryOfOrigin() +
                        "\nStatus: " + i.getImmigrationStatus() +
                        "\nAddress: " + a.getStreet() + ", " + a.getCity() + ", " + a.getState() + " " + a.getZip() +
                        "\nPayment Complete: " + form.isComplete();

                dialog.setContentText(content);
                dialog.getDialogPane().getButtonTypes().addAll(
                        new ButtonType("Approve", ButtonBar.ButtonData.OK_DONE),
                        new ButtonType("Reject", ButtonBar.ButtonData.NO),
                        new ButtonType("Edit", ButtonBar.ButtonData.OTHER),
                        ButtonType.CLOSE
                );

                dialog.showAndWait().ifPresent(response -> {
                    if (response.getText().equals("Approve")) {
                        i.updateStatus("Approved");
                        selected.immigrationStatus.set("Approved");
                    } else if (response.getText().equals("Reject")) {
                        i.updateStatus("Rejected");
                        selected.immigrationStatus.set("Rejected");
                    } else if (response.getText().equals("Edit")) {
                        TextInputDialog editDialog = new TextInputDialog(i.getImmigrationStatus());
                        editDialog.setHeaderText("Edit Immigration Status for " + i.getFullName());
                        editDialog.setContentText("New Status:");
                        editDialog.showAndWait().ifPresent(newStatus -> {
                            i.updateStatus(newStatus);
                            selected.immigrationStatus.set(newStatus);
                        });
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select an application first.").showAndWait();
            }
        });

        stage.setScene(new Scene(layout, 550, 500));
        stage.setTitle("Approver Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
