package de.blogspot.mszalbach.iss.pageobjects;

import de.blogspot.mszalbach.iss.domain.Security;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

/**
 * Created by foobarkilla on 22.10.16.
 */
@DefaultUrl("http://localhost:8080")
public class SecurityListPage extends PageObject {

    @FindBy(id = "securities")
    WebElement securitiesTable;

    public int getCount() {
        List<Map<Object, String>> rows = rowsFrom(securitiesTable);
        return rows.size();
    }

    public void deleteSeurity(Security security) {
        WebElement deleteButton = find(By.id("delete_" + security.isin));
        deleteButton.click();
    }

    public SecurityEnterPage openCreateSecurityDialog() {
        WebElement openCreateButton = find(By.id("openCreate"));
        openCreateButton.click();
        return this.switchToPage(SecurityEnterPage.class);
    }
}
