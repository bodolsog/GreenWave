package pl.bodolsog.greenwave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.bodolsog.greenwave.tools.PropertiesManager;

/**
 * Created by bodolsog on 17.04.16.
 */
public class ConfigDialogController {

    private PropertiesManager prop;
    private Stage stage;
    public boolean okClicked = false;

    @FXML private TextField googleApiKey;

    @FXML
    private void initialize(){
        prop = new PropertiesManager();
        googleApiKey.setText(prop.getGoogleAPIKey());
    }

    public void setDialogStage(Stage dialogStage) {
        stage = dialogStage;
    }

    @FXML
    private void save(){
        okClicked = true;
        prop.setGoogleAPIKey(googleApiKey.getText());
        stage.close();
    }

    @FXML
    private void cancel(){
        stage.close();
    }


}
