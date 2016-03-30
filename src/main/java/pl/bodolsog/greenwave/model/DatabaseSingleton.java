package pl.bodolsog.greenwave.model;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

@SuppressWarnings("deprecation")
public class DatabaseSingleton {
    private static GraphDatabaseService db;
    private static DatabaseSingleton ourInstance = new DatabaseSingleton();

    public static DatabaseSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseSingleton() {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        db = dbFactory.newEmbeddedDatabase("productionDB");
    }

    public GraphDatabaseService getDb(){
        return db;
    }
}