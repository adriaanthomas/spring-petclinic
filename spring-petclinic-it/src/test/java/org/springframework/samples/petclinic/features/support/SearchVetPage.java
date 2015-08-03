package org.springframework.samples.petclinic.features.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A page object that contains all user interface interactions with the page for searching vets.
 */
public class SearchVetPage {
    private final static String URL = "/vets.html";

    @FindBy(css = "input[type='search']")
    @CacheLookup
    private WebElement searchBox;

    @FindBy(css = "table#vets tbody tr")
    private List<WebElement> vetRows;

    public SearchVetPage(WebDriver driver, String baseUrl) {
        // navigate to the correct page
        driver.get(baseUrl + URL);

        // fill all fields (instance variables) defined above
        PageFactory.initElements(driver, this);
    }

    public void search(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public List<String> getVetNames() {
        List<String> names = new ArrayList<>(vetRows.size());
        for (WebElement row : vetRows) {
            // fill with the first td
            names.add(row.findElement(By.tagName("td")).getText());
        }
        return Collections.unmodifiableList(names);
    }
}
