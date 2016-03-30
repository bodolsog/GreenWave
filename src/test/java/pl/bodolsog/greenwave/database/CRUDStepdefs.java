package pl.bodolsog.greenwave.database;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.neo4j.graphdb.*;
import org.neo4j.test.TestGraphDatabaseFactory;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;
import pl.bodolsog.greenwave.model.DatabaseSingleton;
import pl.bodolsog.greenwave.model.Nodes;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CRUDStepdefs {
    private GraphDatabaseService db;
    private Crosses crosses;
    private ArrayList<Long> ids;

    @Before("@db")
    public void setUpDatabase(){
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        crosses = new Crosses(db);
        ids = new ArrayList<>();
    }

    @After("@db")
    private void closeDatabase(){
        db.shutdown();
    }

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

    @When("^the App User add the cross$")
    public void the_App_User_add_a() throws Throwable {
        crosses.create(new Cross());
    }

    @When("^the App User delete the cross$")
    public void the_App_User_delete_a_cross_es() throws Throwable {
        crosses.delete(ids.get(0));
        ids.remove(0);
    }

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
