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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CRUDStepdefs {
    private GraphDatabaseService db;

    @Before("@db")
    public void setUpDatabase(){
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
    }

    @After("@db")
    private void closeDatabase(){
        db.shutdown();
    }

    @Given("^the database with (\\d+) crosses$")
    public void the_database_with_crosses(int init_state) throws Throwable {
        try (Transaction tx = db.beginTx()) {
            for (int i = 0; i < init_state; i++) {
                db.createNode(Nodes.CROSS);
            }
            tx.success();
        }
    }

    @When("^the App User add a (\\d+)$")
    public void the_App_User_add_a(int added_crosses) throws Throwable {
        for(int i = 0; i < added_crosses; i++) {
            new Crosses(db).create(new Cross());
        }
    }

    @Then("^the database have (\\d+) crosses$")
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
