package de.blogspot.mszalbach.iss.pageobjects;

import de.blogspot.mszalbach.iss.domain.Security;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static net.thucydides.core.pages.components.HtmlTable.rowsFrom;

/**
 * Created by foobarkilla on 22.10.16.
 */
@DefaultUrl("http://localhost:8080/#/securities")
public class SecurityListPage extends PageObject {

    public int getCount() {
        List<Map<Object, String>> rows = rowsFrom(find(By.id("securities")));
        return rows.size();
    }

    public void deleteSecurity(Security security) {
        WebElement deleteButton = find(By.id("delete_" + security.getIsin()));
        deleteButton.click();
    }

    public SecurityEnterPage openCreateSecurityDialog() {
        WebElement openCreateButton = find(By.id("openCreate"));
        openCreateButton.click();
        return this.switchToPage(SecurityEnterPage.class);
    }

    public String loggedInAs() {
        WebElement userdropdown = find(By.id("userdropdown"));
        userdropdown.click();
        return userdropdown.getText();
    }
}
