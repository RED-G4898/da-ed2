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

    // Tabla de contactos
    @FXML private TableView<Contacto> contactoTable;
    @FXML private TableColumn<Contacto, String> contactoTipo;
    @FXML private TableColumn<Contacto, String> contactoNombre;
    @FXML private TableColumn<Contacto, String> contactoFechaRecordatorio;
    @FXML private TableColumn<Contacto, String> contactoFecha;

    // Labels de contactos
    @FXML
    private Label contactoTipoLabel;
    @FXML
    private Label contactoNombreLabel;
    @FXML
    private Label contactoFechaLabel;
    @FXML
    private Label contactoFechaRecordatorioLabel;


    // Tabla de recordatorios
    @FXML private TableView<Recordatorio> recordatorioTable;
    @FXML private TableColumn<Recordatorio, String> recordatorioTipo;
    @FXML private TableColumn<Recordatorio, String> recordatorioNombre;
    @FXML private TableColumn<Recordatorio, String> recordatorioFechaRecordatorio;
    @FXML private TableColumn<Recordatorio, String> recordatorioFecha;

    // Labels de recordatorios
    @FXML
    private Label recordatorioTipoLabel;
    @FXML
    private Label recordatorioNombreLabel;
    @FXML
    private Label recordatorioFechaLabel;
    @FXML
    private Label recordatorioFechaRecordatorioLabel;

    // Reference to the main application.
    private AgendaFXApplication mainApp;

    public ObservableList<Contacto> contactoList = FXCollections.observableArrayList(
            new Contacto("1", "Nava", "1-11-2020", "1-10-2020"),
            new Contacto("2",  "Fahim", "1-12-2021", "1-10-2020"),
            new Contacto("3",  "Shariful", "3-10-2022", "1-10-2020"),
            new Contacto("4",  "Alfonso", "3-10-2022", "1-10-2020")
    );

    public ObservableList<Recordatorio> recordatorioList = FXCollections.observableArrayList(
            new Recordatorio("1", "Noche buena", "24-12-2020", "24-12-2020"),
            new Recordatorio("2",  "Navidad", "25-12-2021", "25-12-2021"),
            new Recordatorio("3",  "Cena de año nuevo", "31-12-2022", "31-12-2022"),
            new Recordatorio("4",  "Año nuevo", "01-01-2023", "01-01-2023")
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        contactoTipo.setCellValueFactory(new PropertyValueFactory<Contacto, String>("tipo"));
        contactoNombre.setCellValueFactory(new PropertyValueFactory<Contacto, String>("nombre"));
        contactoFechaRecordatorio.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fechaRecordatorio"));
        contactoFecha.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fecha"));
        contactoTable.setItems(contactoList);

        // Clear contact details.
        showContactoDetails(null);

        // Listen for selection changes and show the person details when changed.
        contactoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactoDetails(newValue));

        recordatorioTipo.setCellValueFactory(new PropertyValueFactory<Recordatorio, String>("tipo"));
        recordatorioNombre.setCellValueFactory(new PropertyValueFactory<Recordatorio, String>("nombre"));
        recordatorioFechaRecordatorio.setCellValueFactory(new PropertyValueFactory<Recordatorio, String>("fechaRecordatorio"));
        recordatorioFecha.setCellValueFactory(new PropertyValueFactory<Recordatorio, String>("fecha"));
        recordatorioTable.setItems(recordatorioList);

        // Clear details.
        showRecordatorioDetails(null);

        // Listen for selection changes and show the person details when changed.
        recordatorioTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRecordatorioDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(AgendaFXApplication mainApp) {
        this.mainApp = mainApp;

        // Add observable contactoList data to the contactoTable
        contactoTable.setItems(contactoList);

        // Add observable recordatorioList data to the recordatorioTable
        recordatorioTable.setItems(recordatorioList);
    }




    private void showContactoDetails(Contacto contacto) {
        if (contacto != null) {
            // Fill the labels with info from the contacto object.
            contactoTipoLabel.setText(contacto.getTipo());
            contactoNombreLabel.setText(contacto.getNombre());
            contactoFechaRecordatorioLabel.setText(contacto.getFechaRecordatorio());
            contactoFechaLabel.setText(contacto.getFecha());
        } else {
            // Contacto is null, remove all the text.
            contactoTipoLabel.setText("");
            contactoNombreLabel.setText("");
            contactoFechaRecordatorioLabel.setText("");
            contactoFechaLabel.setText("");
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
            contactoList.add(tempContacto);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditContacto() {
        Contacto selectedContacto = contactoTable.getSelectionModel().getSelectedItem();
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
            alert.setContentText("Please select a person in the contactoTable.");

            alert.showAndWait();
        }
    }

    private void showRecordatorioDetails(Recordatorio recordatorio) {
        if (recordatorio != null) {
            // Fill the labels with info from the recordatorio object.
            recordatorioTipoLabel.setText(recordatorio.getTipo());
            recordatorioNombreLabel.setText(recordatorio.getNombre());
            recordatorioFechaRecordatorioLabel.setText(recordatorio.getFechaRecordatorio());
            recordatorioFechaLabel.setText(recordatorio.getFecha());
        } else {
            // Recordatorio is null, remove all the text.
            recordatorioTipoLabel.setText("");
            recordatorioNombreLabel.setText("");
            recordatorioFechaRecordatorioLabel.setText("");
            recordatorioFechaLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */

    @FXML
    private void handleNewRecordatorio() {
        Recordatorio tempRecordatorio = new Recordatorio();
        boolean okClicked = mainApp.showRecordatorioEdicionDialogo(tempRecordatorio);
        if (okClicked) {
            recordatorioList.add(tempRecordatorio);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */

    @FXML
    private void handleEditRecordatorio() {
        Recordatorio selectedRecordatorio = recordatorioTable.getSelectionModel().getSelectedItem();
        if (selectedRecordatorio != null) {
            boolean okClicked = mainApp.showRecordatorioEdicionDialogo(selectedRecordatorio);
            if (okClicked) {
                showRecordatorioDetails(selectedRecordatorio);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Recordatorio Selected");
            alert.setContentText("Please select a person in the recordatorioTable.");

            alert.showAndWait();
        }
    }
}