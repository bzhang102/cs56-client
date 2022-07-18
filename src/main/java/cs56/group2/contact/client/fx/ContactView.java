package cs56.group2.contact.client.fx;

import cs56.group2.contact.client.service.ContactRestClientService;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContactView extends Application {

    public void start(Stage stage) throws IOException {
        ContactRestClientService service = new ContactRestClientService();

        // get data from csv
        ObservableList<ContactFx> data = FXCollections.observableArrayList(service.getAllContacts());

        // create all the columns
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<ContactFx, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactFx, String>>() {
                @Override
                public void handle(CellEditEvent<ContactFx, String> t) {
                    ((ContactFx) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setFirstName(t.getNewValue());
                }
            }
        );

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<ContactFx, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactFx, String>>() {
                @Override
                public void handle(CellEditEvent<ContactFx, String> t) {
                    ((ContactFx) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setLastName(t.getNewValue());
                }
            }
        );

        TableColumn middleNameCol = new TableColumn("Middle Name");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<ContactFx, String>("middleName"));
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactFx, String>>() {
                @Override
                public void handle(CellEditEvent<ContactFx, String> t) {
                    ((ContactFx) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setMiddleName(t.getNewValue());
                }
            }
        );

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setPrefWidth(300);
        emailCol.setCellValueFactory(new PropertyValueFactory<ContactFx, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactFx, String>>() {
                @Override
                public void handle(CellEditEvent<ContactFx, String> t) {
                    ((ContactFx) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setEmail(t.getNewValue());
                }
            }
        );

        TableColumn cellCol = new TableColumn("Cell");
        cellCol.setPrefWidth(150);
        cellCol.setCellValueFactory(new PropertyValueFactory<ContactFx, String>("cell"));
        cellCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cellCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ContactFx, String>>() {
                @Override
                public void handle(CellEditEvent<ContactFx, String> t) {
                    ((ContactFx) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                    ).setCell(t.getNewValue());
                }
            }
        );

        // create tableview and add data
        TableView<ContactFx> table = new TableView<ContactFx>();
        table.setEditable(true);
        table.setItems(data);

        // add columns to table
        table.getColumns().addAll(firstNameCol, lastNameCol, middleNameCol, emailCol, cellCol);

        // create filter
        final Label filterLabel = new Label("Search");
        //filterLabel.setFont(new Font("Arial", 12));
        final TextField filterField = new TextField();
        ObservableList items = table.getItems();
        filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                table.setItems(items);
            }
            String value = newValue.toLowerCase();
            ObservableList<ContactFx> subentries = FXCollections.observableArrayList();

            long count = table.getColumns().stream().count();
            for (int i = 0; i < table.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + table.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(table.getItems().get(i));
                        break;
                    }
                }
            }
            table.setItems(subentries);
        });

        // show all data
        final Button allButton = new Button("All");
        allButton.setOnAction(e -> {
            filterField.clear();
            table.setItems(data);
        });

        // add contact
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setPromptText("Last Name");
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        final TextField addMiddleName = new TextField();
        addMiddleName.setPromptText("Mid Name");
        addMiddleName.setMaxWidth(middleNameCol.getPrefWidth());
        final TextField addEmail = new TextField();
        addEmail.setPromptText("Email");
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPrefWidth(200);
        final TextField addCell = new TextField();
        addCell.setPromptText("Cell");
        addCell.setPrefWidth(cellCol.getPrefWidth());
        addCell.setMaxWidth(cellCol.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            ContactFx contact = new ContactFx();
            contact.setFirstName(addFirstName.getText());
            contact.setLastName(addLastName.getText());
            contact.setMiddleName(addMiddleName.getText());
            contact.setEmail(addEmail.getText());
            contact.setCell(addCell.getText());

            data.add(contact);
            table.setItems(data);

            addFirstName.clear();
            addLastName.clear();
            addMiddleName.clear();
            addEmail.clear();
            addCell.clear();
        });

        // delete contact
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            ContactFx selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            data.remove(selectedItem);
        });

        // save to CSV
        final Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                service.updateAllContacts(table.getItems());
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });

        final HBox hb1 = new HBox();
        hb1.getChildren().addAll(filterLabel, filterField, allButton);
        hb1.setSpacing(20);

        // put everything on screen
        final HBox hb2 = new HBox();
        hb2.getChildren().addAll(addFirstName, addLastName, addMiddleName, addEmail, addCell,
            addButton, deleteButton, saveButton);
        hb2.setSpacing(3);

        Scene scene = new Scene(new Group());
        stage.setTitle("Contact Management System");
        stage.setWidth(1000);
        stage.setHeight(500);

        final VBox vBox = new VBox();

        vBox.setPrefWidth(800);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.getChildren().addAll(hb1, table, hb2);

        ((Group) scene.getRoot()).getChildren().addAll(vBox);

        stage.setScene(scene);
        stage.show();
    }
}
