package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.neo4j.graphdb.NotFoundException;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ReadStepdefs {

    private Crosses crosses;
    private Cross readedCross;

    public ReadStepdefs(DatabaseHelper dbh){
       crosses = dbh.getCrosses();
    }

    /**
     * Read cross with specific id (via interface and Dao).
     * @param id int    id of readed cross
     * @throws Throwable
     */
    @When("^the App User try to get cross with id (\\d+)$")
    public void the_App_User_try_to_get_cross_with_id(long id) throws Throwable {
        readedCross = crosses.read(id);
    }

    /**
     * Check if readed cross are right one.
     * @param id int    id of readed cross
     * @throws Throwable
     */
    @Then("^a cross with (\\d+) is returned$")
    public void a_cross_with_id_is_returned(long id) throws Throwable {
        assertThat(readedCross.getId(), is(id));
    }
}
