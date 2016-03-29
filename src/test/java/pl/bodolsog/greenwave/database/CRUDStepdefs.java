package pl.bodolsog.greenwave.database;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by bodolsog on 29.03.16.
 */
public class CRUDStepdefs {
    @Given("^the database with (\\d+) crosses$")
    public void the_database_with_crosses(int arg1) throws Throwable {
    }

    @When("^the App User add a (\\d+)$")
    public void the_App_User_add_a(int arg1) throws Throwable {
    }

    @Then("^the database have (\\d+) crosses$")
    public void the_database_have_crosses(int arg1) throws Throwable {
    }
}
