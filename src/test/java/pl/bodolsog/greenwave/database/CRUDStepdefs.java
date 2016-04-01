package pl.bodolsog.greenwave.database;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import org.neo4j.graphdb.*;
import org.neo4j.test.TestGraphDatabaseFactory;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;
import pl.bodolsog.greenwave.model.DatabaseSingleton;
import pl.bodolsog.greenwave.model.Nodes;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CRUDStepdefs {
    private GraphDatabaseService db;
    private Crosses crosses;
    private ArrayList<Long> ids;
    @Mock private Cross crossMock;
    private Cross readedCross;

    /**
     * Seting up temporary database for test.
     *
     */
    @Before("@db")
    public void setUpDatabase(){
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        crosses = new Crosses(db);
        ids = new ArrayList<>();
    }

    /**
     * Shut down temporary database after test.
     */
    @After("@db")
    private void closeDatabase(){
        db.shutdown();
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
     * Add a cross (via interface and Dao).
     * @throws Throwable
     */
    @When("^the App User add the cross$")
    public void the_App_User_add_a() throws Throwable {
        crosses.create(crossMock);
    }

    /**
     * Delete the cross (via interface and Dao).
     * @throws Throwable
     */
    @When("^the App User delete the cross with id (\\d+)$")
    public void the_App_User_delete_a_cross_es(long id) throws Throwable {
        crosses.delete(id);
        ids.remove(id);
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

    /**
     * Read cross with specific id (via interface and Dao).
     * @param id int    id of readed cross
     * @throws Throwable
     */
    @When("^the App User try to get cross with id (\\d+)$")
    public void theAppUserWantGetCrossWithId(long id) throws Throwable {
        readedCross = crosses.read(id);
    }

    /**
     * Check if readed cross are right one.
     * @param id int    id of readed cross
     * @throws Throwable
     */
    @Then("^a cross with (\\d+) is returned$")
    public void aCrossWithIdIsReturned(long id) throws Throwable {
        assertThat(readedCross.getId(), is(id));
    }

    /**
     * Check if returned value is null
     * @throws Throwable
     */
    @Then("^a null is returned$")
    public void aNullIsReturned() throws Throwable {
        assertThat(readedCross, is(nullValue()));
    }
}
