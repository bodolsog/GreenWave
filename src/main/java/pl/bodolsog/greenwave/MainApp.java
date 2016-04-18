package pl.bodolsog.greenwave;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.bodolsog.greenwave.controller.ConfigDialogController;
import pl.bodolsog.greenwave.controller.MapViewController;
import pl.bodolsog.greenwave.tools.PropertiesManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private static MapViewController mapViewController;

    // Starts app
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GreenWaveFX");

        // Init layout
        initRootLayout();
        showMapView();
        PropertiesManager p = new PropertiesManager();
    }


    /**
     * Initializes root layout.
     */
    private void initRootLayout(){
        try {
            // Load root layout from fxml file.
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainAppView.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout, 1600, 900);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Show map in root layout.
     */
    private void showMapView(){
        try {
            // Load map from fxml file.
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MapView.fxml"));
            WebView mapView = loader.load();

            // Give the controller access to the main app.
            mapViewController = loader.getController();

            // Show the scene containing the root layout.
            rootLayout.setCenter(mapView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void closeApp(){
        Platform.exit();
    }

    @FXML
    private void openPropertiesDialog(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConfigDialog.fxml"));
            VBox page = (VBox) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Properties");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            ConfigDialogController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();
            mapViewController.reloadWebView();

        } catch ( IOException e ){
            e.printStackTrace();
        }
    }
}
