package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class CreateStepdefs {

    private Crosses crosses;
    private Cross cross;
    @Mock private Cross crossMock;
    private HashMap<String, Long> ids;

    public CreateStepdefs(DatabaseHelper dbh){
        crosses = dbh.getCrosses();
        ids = dbh.getIds();
    }

    // Add a cross (via interface and Dao).
    @When("^the App User add a cross$")
    public void the_App_User_add_a_cross() {
        crosses.create(crossMock);
    }

    // Add cross with lat, lng
    @When("^the App User add the cross with latitude (\\d+.\\d+) and longitude (\\d+.\\d+)$")
    public void the_App_User_add_the_cross_with_latitude_and_longitude(double lat, double lng) {
        cross = new Cross(lat, lng);
        ids.put("the_App_User_add_the_cross_with_latitude_and_longitude", crosses.create(cross));
    }

    // Verify cross with lat, lng
    @Then("^added Cross have latitude (\\d+.\\d+) and longitude (\\d+.\\d+)$")
    public void added_cross_have_latitude_and_longitude(double lat, double lng) {
        Cross actCross = crosses.read(ids.get("the_App_User_add_the_cross_with_latitude_and_longitude"));
        assertThat("Latitude don't match.", actCross.getLat(), is(lat));
        assertThat("Longitude don't match.", actCross.getLng(), is(lng));
    }

}
