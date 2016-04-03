package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
