package pl.bodolsog.greenwave.database.CRUD;

import cucumber.api.java.en.When;
import org.mockito.Mock;
import pl.bodolsog.greenwave.database.DatabaseHelper;
import pl.bodolsog.greenwave.model.Cross;
import pl.bodolsog.greenwave.model.Crosses;


public class CreateStepdefs {
    private Crosses crosses;
    @Mock
    private Cross crossMock;

    public CreateStepdefs(DatabaseHelper dbh){
        crosses = dbh.getCrosses();
    }

    /**
     * Add a cross (via interface and Dao).
     * @throws Throwable
     */
    @When("^the App User add the cross$")
    public void the_App_User_add_a() throws Throwable {
        crosses.create(crossMock);
    }
}
