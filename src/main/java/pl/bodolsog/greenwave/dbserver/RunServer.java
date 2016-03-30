package pl.bodolsog.greenwave.dbserver;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.neo4j.server.configuration.Configurator;
import org.neo4j.server.configuration.ServerConfigurator;

@SuppressWarnings("deprecation")
public class RunServer {
    public static void main(String[] args) {
        WrappingNeoServerBootstrapper neoServerBootstrapper;
        GraphDatabaseService db = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder("db").newGraphDatabase();

        try {
            GraphDatabaseAPI api = (GraphDatabaseAPI) db;

            ServerConfigurator config = new ServerConfigurator(api);
            config.configuration()
                    .addProperty(Configurator.WEBSERVER_ADDRESS_PROPERTY_KEY, "127.0.0.1");
            config.configuration()
                    .addProperty(Configurator.WEBSERVER_PORT_PROPERTY_KEY, "7575");

            neoServerBootstrapper = new WrappingNeoServerBootstrapper(api, config);
            neoServerBootstrapper.start();
        } catch (Exception e) {
            //handle appropriately
        }
    }
}
