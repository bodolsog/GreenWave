package pl.bodolsog.greenwave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import pl.bodolsog.greenwave.tools.PropertiesManager;

/**
 * Controller for dialog that manages configuration and PropertiesManager.
 */
public class ConfigDialogController {

    private PropertiesManager prop;
    private Stage stage;
    private WebEngine webEngine;

    @FXML private TextField googleApiKey;

    @FXML
    private void initialize(){
        prop = new PropertiesManager();
        googleApiKey.setText(prop.getGoogleAPIKey());
    }

    /**
     * Inject Stage.
     *
     * @param dialogStage Stage.
     */
    public void setDialogStage(Stage dialogStage) {
        stage = dialogStage;
    }


    /**
     * Inject WebEngine.
     *
     * @param webEngine WebEngine.
     */
    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

    /**
     * Save new GoogleAPIKey.
     */
    @FXML
    private void save(){
        boolean result = prop.setGoogleAPIKey(googleApiKey.getText());

        // If succesed and webEngine is injected - reload page (need to refresh key)
        if (result && webEngine != null){
            webEngine.reload();
        }

        // Close stage.
        stage.close();
    }

    /**
     * React on cancel button.
     */
    @FXML
    private void cancel(){
        stage.close();
    }

}
