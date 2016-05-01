package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UpdateStepdefs {
    private Crosses crosses;
    @Mock
    private Cross crossMock;
    private HashMap<String, Long> ids;

    public UpdateStepdefs(DatabaseHelper dbh){
        crosses = dbh.getCrosses();
        ids = dbh.getIds();
    }
    @When("^the App User update the cross with new latitude (\\d+\\.\\d+) and longitude (\\d+\\.\\d+)$")
    public void the_App_User_update_the_cross_with_new_latitude_and_longitude(double lat, double lng)  {
        Cross cross = crosses.read(ids.get("the_App_User_add_the_cross_with_latitude_and_longitude"));
        cross.setLat(lat);
        cross.setLng(lng);
        crosses.update(cross);
    }

    @Then("^updated Cross have latitude (\\d+\\.\\d+) and longitude (\\d+\\.\\d+)$")
    public void updated_Cross_have_latitude_and_longitude(double lat, double lng) {
        Cross cross = crosses.read(ids.get("the_App_User_add_the_cross_with_latitude_and_longitude"));
        assertThat("Latitude isn't right updated.", cross.getLat(), is(lat));
        assertThat("Longitude isn't right updated.", cross.getLng(), is(lng));
    }
}
