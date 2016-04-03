package pl.bodolsog.greenwave.database;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class DatabaseRunner {
    private GraphDatabaseService db;

    /**
     * Seting up temporary database for test.
     *
     */
    @Before("@db")
    public void setUpDatabase(){
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
    }

    /**
     * Shut down temporary database after test.
     */
    @After("@db")
    public void closeDatabase(){
        db.shutdown();
    }

    /**
     * Getter for database.
     * @return GraphDatabaseService db
     */
    public GraphDatabaseService getDb(){
        return db;
    }

}
