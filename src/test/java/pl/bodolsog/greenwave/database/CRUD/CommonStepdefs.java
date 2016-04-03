package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.neo4j.graphdb.*;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Nodes;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommonStepdefs {

    private GraphDatabaseService db;
    private ArrayList<Long> ids;

    public CommonStepdefs(DatabaseHelper dbh){
        db = dbh.getDb();
        ids = dbh.getIds();
    }

    /**
     * Set up initial database state (how many crosses are in).
     * @param init_state int    how many crosses should add to database
     * @throws Throwable
     */
    @Given("^the database with (\\d+) cross\\(es\\)$")
    public void the_database_with_crosses(int init_state) throws Throwable {
        try (Transaction tx = db.beginTx()) {
            Node n = null;
            for (int i = 0; i < init_state; i++) {
                n = db.createNode(Nodes.CROSS);
                ids.add(n.getId());
            }
            tx.success();
        }
    }


    /**
     * Check if count of crosses in database is right.
     * @param end_state int     how many crosses should be in database after scenario
     * @throws Throwable
     */
    @Then("^the database have (\\d+) cross\\(es\\)$")
    public void the_database_have_crosses(int end_state) throws Throwable {
        int count = 0;
        try (Transaction tx = db.beginTx()) {
            ResourceIterator<Node> res = db.findNodes(Nodes.CROSS);
            while (res.hasNext()) {
                count++;
                res.next();
            }
            tx.success();
        }
        assertThat(count, is(end_state));
    }
}
