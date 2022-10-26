package uia.com.agendafx.agendafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaFXController implements Initializable {

    @FXML private TableView<Contacto> table;
    @FXML private TableColumn<Contacto, String> tipo;
    @FXML private TableColumn<Contacto, String> nombre;
    @FXML private TableColumn<Contacto, String> fechaRecordatorio;
    @FXML private TableColumn<Contacto, String> fecha;
    @FXML
    private Label tipoLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label fechaRecordatorioLabel;

    // Reference to the main application.
    private AgendaFXApplication mainApp;

    public ObservableList<Contacto> list = FXCollections.observableArrayList(
            new Contacto("1", "Nava", "1-11-2020", "1-10-2020"),
            new Contacto("2",  "Fahim", "1-12-2021", "1-10-2020"),
            new Contacto("3",  "Shariful", "3-10-2022", "1-10-2020"),
            new Contacto("4",  "Alfonso", "3-10-2022", "1-10-2020")
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipo.setCellValueFactory(new PropertyValueFactory<Contacto, String>("tipo"));
        nombre.setCellValueFactory(new PropertyValueFactory<Contacto, String>("nombre"));
        fechaRecordatorio.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fechaRecordatorio"));
        fecha.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fecha"));
        table.setItems(list);

        // Clear person details.
        showContactoDetails(null);

        // Listen for selection changes and show the person details when changed.
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactoDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(AgendaFXApplication mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        table.setItems(list);
    }




    private void showContactoDetails(Contacto contacto) {
        if (contacto != null) {
            // Fill the labels with info from the contacto object.
            tipoLabel.setText(contacto.getTipo());
            nombreLabel.setText(contacto.getNombre());
            fechaRecordatorioLabel.setText(contacto.getFechaRecordatorio());
            fechaLabel.setText(contacto.getFecha());
        } else {
            // Contacto is null, remove all the text.
            tipoLabel.setText("");
            nombreLabel.setText("");
            fechaRecordatorioLabel.setText("");
            fechaLabel.setText("");
        }
    }


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewContacto() {
        Contacto tempContacto = new Contacto();
        boolean okClicked = mainApp.showContactoEdicionDialogo(tempContacto);
        if (okClicked) {
            list.add(tempContacto);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditContacto() {
        Contacto selectedContacto = table.getSelectionModel().getSelectedItem();
        if (selectedContacto != null) {
            boolean okClicked = mainApp.showContactoEdicionDialogo(selectedContacto);
            if (okClicked) {
                showContactoDetails(selectedContacto);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Contacto Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


}