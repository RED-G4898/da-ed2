package uia.com.agendafx.agendafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class AgendaFXApplication extends Application {

    private Stage primaryStage;
    private BorderPane layoutRaiz;

    public  Window getPrimaryStage() {
        return this.primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda");
        initLayoutRaiz();
        showContactoOverview();
    }
    /**
     * Initializes the root layout.
     */
    public void initLayoutRaiz() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("layoutRaizFX.fxml"));
            layoutRaiz = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(layoutRaiz);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showContactoEdicionDialogo(Contacto newContacto) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("ContactoEdicionDialogo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edición de Contacto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the newContacto into the controller.
            ContactoEdicionDialogoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setContacto(newContacto);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Shows the contacto overview inside the root layout.
     */
    public void showContactoOverview() {
        try {
            // Load contacto overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("AgendaFX.fxml"));
            AnchorPane contactoOverview = (AnchorPane) loader.load();

            // Set contacto overview into the center of root layout.
            layoutRaiz.setCenter(contactoOverview);

            // Give the controller access to the main app.
            AgendaFXController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showRecordatorioEdicionDialogo(Recordatorio newRecordatorio) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("RecordatorioEdicionDialogo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edición de Recordatorio");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the newRecordatorio into the controller.
            RecordatorioEdicionDialogoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRecordatorio(newRecordatorio);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showRecordatorioOverview() {
        try {
            // Load recordatorio overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("AgendaFX.fxml"));
            AnchorPane recordatorioOverview = (AnchorPane) loader.load();

            // Set recordatorio overview into the center of root layout.
            layoutRaiz.setCenter(recordatorioOverview);

            // Give the controller access to the main app.
            AgendaFXController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}