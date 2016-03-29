package pl.bodolsog.greenwave.database;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by bodolsog on 29.03.16.
 */
public class DatabasePreparation {
    /**
     * This will estate connection to database.
     */
    @Before("@db")
    public void setUpDatabase(){

    }

    /**
     * This will close connection to database.
     */
    @After("@db")
    public void tearDownDatabase(){

    }
}
