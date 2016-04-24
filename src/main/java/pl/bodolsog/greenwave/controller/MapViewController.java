package pl.bodolsog.greenwave.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import pl.bodolsog.greenwave.MainApp;
import pl.bodolsog.greenwave.tools.PropertiesManager;

public class MapViewController {

    // Reference to main app.
    private MainApp mainApp;

    @FXML
    private WebView webView;

    private static WebEngine webEngine;
    private static PropertiesManager prop = new PropertiesManager();
    private JSObject window;

    /**
     * Initializes view.
     */
    @FXML
    private void initialize(){
        webView.setContextMenuEnabled(false);

        // Initializes web engine and Google Maps into.
        webEngine = webView.getEngine();

        // Set up window and controller.
        window = (JSObject) webEngine.executeScript("window");
        window.setMember("controller", this);

        // Load view.
        webEngine.load(getClass().getResource("/googlemap.html").toString());

        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                if(event.getData().equals("command:injectGoogleMapsAPI")) {
                    window.setMember("apiKey", prop.getGoogleAPIKey(true));
                }
            }
        });
    }

    /**
     * Getter for property 'webEngine'.
     *
     * @return Value for property 'webEngine'.
     */
    public static WebEngine getWebEngine() {
        return webEngine;
    }
}