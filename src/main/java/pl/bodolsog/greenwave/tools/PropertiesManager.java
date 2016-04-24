package pl.bodolsog.greenwave.tools;

import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebView;

import java.io.*;
import java.util.Optional;
import java.util.Properties;


/**
 * Handles properties from config file.
 *
 */
public class PropertiesManager {

    // Path to $HOME/.grenwave directory
    private String propertiesPath = System.getProperty("user.home") +File.separator +".greenwave";

    // Path to config file
    private String propertiesFilepath = propertiesPath + File.separator + "config";

    // Config file
    private File configFile;

    // Properties reference
    private Properties properties;


    /**
     * Constructor. Open properties file and load all properties into memory. If file not exist - show dialog that ask
     * about GoogleMaps API Key and save it to new created config file.
     *
     */
    public PropertiesManager() {

        // Initialize properties.
        properties = new Properties();

        // Create necessary dirs.
        File propertiesDir = new File(propertiesPath);
        if (!propertiesDir.exists())
            propertiesDir.mkdirs();

        configFile = new File(propertiesFilepath);
        if ( !configFile.exists() ){
            // Dialog for set GoogleMaps API key.
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("GoogleMaps Api Key");
            dialog.setHeaderText("GoogleMaps API Key must be set.");
            dialog.setContentText("Your GoogleMaps API Key:");

            Optional<String> result = dialog.showAndWait();

            // Save GoogleMaps API Key to config file.
            try {
                configFile.createNewFile();
                FileOutputStream out = new FileOutputStream(configFile);
                result.ifPresent(api -> properties.setProperty("googleAPIKey", api));
                properties.store(out, "");
                out.close();
            } catch (IOException e) {
                System.out.println("Sorry, could not create or load config file.");
            }
        }

        // Open config file.
        try {
            FileInputStream in = new FileInputStream(configFile);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("Sorry, could not load config file.");
        }
    }


    /**
     * Read GoogleMaps API Key from file.
     *
     * @return String Google API key
     */
    public String getGoogleAPIKey() {
        return getGoogleAPIKey(false);
    }


    /**
     * Reload config and read GoogleMaps API Key from file.
     *
     * @param reload true if config file should be reloaded
     * @return Google API key
     */
    public String getGoogleAPIKey(boolean reload) {
        if(reload) {
            try {
                FileInputStream in = new FileInputStream(configFile);
                properties.load(in);
                in.close();
            } catch (IOException e) {
                System.out.println("Sorry, could not load config file.");
            }
        }
        return properties.getProperty("googleAPIKey");
    }


    /**
     * Set GoogleMaps API key.
     *
     * @param newValue new key
     * @return true if saved, false otherwise
     */
    public boolean setGoogleAPIKey(String newValue) {
        try {
            FileOutputStream out = new FileOutputStream(configFile);
            properties.setProperty("googleAPIKey", newValue);
            properties.store(out, "");
            out.close();
            return true;
        } catch ( IOException e ) {
            e.printStackTrace();
            System.out.println("Sorry, could not open and write config file.");
        }
        return false;
    }
}
