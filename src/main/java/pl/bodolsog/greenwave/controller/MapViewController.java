package pl.bodolsog.greenwave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import pl.bodolsog.greenwave.MainApp;
import pl.bodolsog.greenwave.tools.PropertiesManager;

import java.util.ArrayList;
import java.util.Optional;

public class MapViewController {

    // Reference to main app.
    private MainApp mainApp;

    @FXML
    private WebView webView;
    private WebEngine webEngine;

    /**
     * Initializes view.
     */
    @FXML
    private void initialize(){
        // Initializes web engine and Google Maps into.
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/googlemap.html").toString());

        // Register classes for use from javascript.
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("properties", new PropertiesManager());
        window.setMember("controller", this);
    }
}