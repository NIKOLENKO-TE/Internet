package internet;

import internet.core.TestBase;
import internet.pages.DropdownPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class DropDownTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/dropdown");
    }

    @Test
    public void selectDropdownOptionByNamePositiveTest() {
        String option = "Option 1";
        new DropdownPage(app.driver, app.wait)
                .selectOptionByText(option)
                .verifySelectedOption(option)
        ;
    }

    @Test
    public void selectDropdownOptionByValuePositiveTest() {
        String option = "2";
        new DropdownPage(app.driver, app.wait)
                .selectOptionByValue(option)
                .verifySelectedOption(option)
        ;
    }

    @Test
    public void selectDropdownOptionByIndexPositiveTest() {
        int index = 1;
        String option = "Option " + index;
        new DropdownPage(app.driver, app.wait)
                .selectOptionByIndex(index)
                .verifySelectedOption(option)
        ;
    }

    @Test
    public void selectDropdownOptionByIndexDefaultPositiveTest() {
        new DropdownPage(app.driver, app.wait)
                //.selectOptionByIndex(0)
                .verifySelectedOption("Please select an option")
        ;
    }
}
