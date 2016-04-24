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

    /**
     * Initializes view.
     */
    @FXML
    private void initialize(){
        webView.setContextMenuEnabled(false);
        // Initializes web engine and Google Maps into.
        webEngine = webView.getEngine();
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
            @Override
            public void handle(WebEvent<String> event) {
                if(event.getData().equals("command:inject")) {
                    JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("api", prop.getGoogleAPIKey(true));
                    // Probably unnecesary
                    // window.setMember("properties", prop);
                    window.setMember("controller", this);
                }
            }
        });
        webEngine.load(getClass().getResource("/googlemap.html").toString());
    }

    public void reloadWebView(){
        webEngine.reload();
    }
}