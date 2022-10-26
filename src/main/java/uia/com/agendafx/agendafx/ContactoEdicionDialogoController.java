package uia.com.agendafx.agendafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

    /**
     * Dialog to edit details of a contacto.
     */
    public class ContactoEdicionDialogoController {

        @FXML
        private TextField tipoField;
        @FXML
        private TextField nombreField;
        @FXML
        private TextField fechaField;
        @FXML
        private TextField fechaRecordatorioField;


        private Stage dialogStage;
        private Contacto contacto;
        private boolean okClicked = false;

        /**
         * Initializes the controller class. This method is automatically called
         * after the fxml file has been loaded.
         */
        @FXML
        private void initialize() {
        }

        /**
         * Sets the stage of this dialog.
         *
         * @param dialogStage
         */
        public void setDialogStage(Stage dialogStage) {
            this.dialogStage = dialogStage;
        }

        /**
         * Sets the contacto to be edited in the dialog.
         *
         * @param contacto
         */
        public void setContacto(Contacto contacto) {
            this.contacto = contacto;

            tipoField.setText(contacto.getTipo());
            nombreField.setText(contacto.getNombre());
            fechaField.setText(contacto.getFecha());
            fechaRecordatorioField.setText(contacto.getFechaRecordatorio());
        }

        /**
         * Returns true if the user clicked OK, false otherwise.
         *
         * @return
         */
        public boolean isOkClicked() {
            return okClicked;
        }

        /**
         * Called when the user clicks ok.
         */
        @FXML
        private void handleOk() {
            if (isInputValid()) {

                this.contacto.setTipo(tipoField.getText());
                this.contacto.setNombre(nombreField.getText());
                this.contacto.setFecha(fechaField.getText());
                this.contacto.setFechaRecordatorio(fechaRecordatorioField.getText());

                okClicked = true;
                dialogStage.close();
            }
        }

        /**
         * Called when the user clicks cancel.
         */
        @FXML
        private void handleCancel() {
            dialogStage.close();
        }

        /**
         * Validates the user input in the text fields.
         *
         * @return true if the input is valid
         */
        private boolean isInputValid() {
            String errorMessage = "";

            if (tipoField.getText() == null || tipoField.getText().length() == 0) {
                errorMessage += "No valid first name!\n";
            }
            if (nombreField.getText() == null || nombreField.getText().length() == 0) {
                errorMessage += "No valid last name!\n";
            }
            if (fechaField.getText() == null || fechaField.getText().length() == 0) {
                errorMessage += "No valid fecha!\n";
            }

            if (fechaRecordatorioField.getText() == null || fechaRecordatorioField.getText().length() == 0) {
                errorMessage += "No valid postal code!\n";
            }

            if (errorMessage.length() == 0) {
                return true;
            } else {
                // Show the error message.
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);

                alert.showAndWait();

                return false;
            }
        }
    }

