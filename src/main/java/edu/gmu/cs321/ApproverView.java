package edu.gmu.cs321;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
    public void start(Stage stage) {
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

        //WorkflowService service = new WorkflowService();

        // Load manually inserted mock data for now
        List<ApplicationForm> mockForms = List.of(
            new ApplicationForm("APP-001",
                new Immigrant("1", "Alice Smith", "1985-03-01", "123-45-6789", "Canada", "Pending",
                    new Address("100 Main St", "Fairfax", "VA", "22030")),
                List.of(), 100.0),
            new ApplicationForm("APP-002",
                new Immigrant("2", "Bob Johnson", "1990-07-15", "987-65-4321", "India", "Pending",
                    new Address("200 Elm St", "Arlington", "VA", "22201")),
                List.of(), 150.0)
        );

        for (ApplicationForm form : mockForms) {
            data.add(new FormRow(form));
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
                ButtonType editBtn = new ButtonType("Edit", ButtonBar.ButtonData.OTHER);
                ButtonType approveBtn = new ButtonType("Approve", ButtonBar.ButtonData.OK_DONE);
                ButtonType rejectBtn = new ButtonType("Reject", ButtonBar.ButtonData.NO);
                dialog.getDialogPane().getButtonTypes().addAll(approveBtn, rejectBtn, editBtn, ButtonType.CLOSE);

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == approveBtn) {
                        i.updateStatus("Approved");
                        selected.immigrationStatus.set("Approved");
                    } else if (result.get() == rejectBtn) {
                        i.updateStatus("Rejected");
                        selected.immigrationStatus.set("Rejected");
                    } else if (result.get() == editBtn) {
                        TextInputDialog editDialog = new TextInputDialog(i.getImmigrationStatus());
                        editDialog.setTitle("Edit Status");
                        editDialog.setHeaderText("Edit Immigration Status for " + i.getFullName());
                        editDialog.setContentText("Status:");
                        editDialog.showAndWait().ifPresent(newStatus -> {
                            i.updateStatus(newStatus);
                            selected.immigrationStatus.set(newStatus);
                        });
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an application first.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(layout, 550, 500);
        stage.setScene(scene);
        stage.setTitle("Approver Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
