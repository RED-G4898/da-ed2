package uia.com.agendafx.agendafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecordatorioEdicionDialogoController {
    @FXML
    private TextField tipoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField fechaField;
    @FXML
    private TextField fechaRecordatorioField;

    private Stage dialogStage;
    private Recordatorio recordatorio;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRecordatorio(Recordatorio recordatorio) {
        this.recordatorio = recordatorio;

        tipoField.setText(recordatorio.getTipo());
        nombreField.setText(recordatorio.getNombre());
        fechaField.setText(recordatorio.getFecha());
        fechaRecordatorioField.setText(recordatorio.getFechaRecordatorio());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            recordatorio.setTipo(tipoField.getText());
            recordatorio.setNombre(nombreField.getText());
            recordatorio.setFecha(fechaField.getText());
            recordatorio.setFechaRecordatorio(fechaRecordatorioField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (tipoField.getText() == null || tipoField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fechaField.getText() == null || fechaField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (fechaRecordatorioField.getText() == null || fechaRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            return false;
        }
    }
}
