package org.springframework.samples.petclinic.features;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.samples.petclinic.features.support.SearchVetPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains the implementation of our specification steps from
 * {@code org/springframework/samples/petclinic/features/find_veterinarian.feature}.
 */
public class FindVeterinarianStepDefs {
    private WebDriver driver;

    private final String baseUrl = System.getProperty("target.url", "http://localhost:9966/petclinic/");

    private SearchVetPage page;

    private String vetName;

    @Before
    public void setUp() {
        driver = new PhantomJSDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Given("^a vet with name \"([^\"]*)\"$")
    public void a_vet_with_name(String name) {
        // The default setup uses an in-memory database, which does not leave us with much scope to change things.
        // Therefore, we will just rely on the default data as provided in db/hsqldb/populateDB.sql, but in a real
        // application we would of course never do that...
        Collection<String>  vets = Arrays.asList(
                "James Carter",
                "Helen Leary",
                "Linda Douglas",
                "Rafael Ortega",
                "Henry Stevens",
                "Sharon Jenkins"
        );
        assertTrue("The specified vet name should be part of the test data set: " + name, vets.contains(name));

        this.vetName = name;
    }

    @When("^a user searches for a vet by typing \"([^\"]+)\" in the search box$")
    public void a_user_searches_for_a_vet_by_typing_in_the_search_box(String searchText) {
        page = new SearchVetPage(driver, baseUrl);
        page.search(searchText);
    }

    @Then("^this vet should be the only result$")
    public void the_vet_should_be_the_only_result() {
        List<String> vetNames = page.getVetNames();
        assertEquals("there should be only one result", 1, vetNames.size());
        assertEquals("the vet name should be correct", vetName, vetNames.get(0));
    }
}
