package pl.bodolsog.greenwave.database;

import org.neo4j.graphdb.GraphDatabaseService;
import pl.bodolsog.greenwave.model.Crosses;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper {
    private GraphDatabaseService db;

    protected Crosses crosses;
    private HashMap<String, Long> ids;

    public DatabaseHelper(DatabaseRunner dbr){
        db = dbr.getDb();
    }

    /**
     * Getter for property 'db'.
     *
     * @return Value for property 'db'.
     */
    public GraphDatabaseService getDb() {
        return db;
    }

    /**
     * Getter for property 'crossesIds'.
     *
     * @return Value for property 'crossesIds'.
     */
    public HashMap<String, Long> getIds() {
        if(ids == null)
            ids = new HashMap<>();
        return ids;
    }

    /**
     * Getter for property 'crosses'.
     *
     * @return Value for property 'crosses'.
     */
    public Crosses getCrosses() {
        if(crosses == null)
            crosses = new Crosses(db);
        return crosses;
    }
}
