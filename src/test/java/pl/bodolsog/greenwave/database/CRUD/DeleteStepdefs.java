package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.When;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Crosses;

import java.util.ArrayList;

public class DeleteStepdefs {

    private Crosses crosses;
    private ArrayList<Long> ids;

    public DeleteStepdefs(DatabaseHelper dbh){
        ids = dbh.getIds();
        crosses = dbh.getCrosses();
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
}
