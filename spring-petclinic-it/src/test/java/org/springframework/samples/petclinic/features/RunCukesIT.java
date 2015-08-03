package org.springframework.samples.petclinic.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Entry point for Cucumber testing.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {
        "pretty", "json:target/cucumber-report.json", "html:target/cucumber-html-report"
})
public class RunCukesIT {
    // nothing
}
