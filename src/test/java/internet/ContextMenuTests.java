package internet;

import internet.core.TestBase;
import internet.pages.ContextMenuPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class ContextMenuTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/context_menu");
    }

    @Test
    public void rightClickOnBoxAndAlertPositiveTest(){
        new ContextMenuPage(app.driver,app.wait)
                .rightClickOnBox()
                .verifyAlertText("You selected a context menu")
                ;
    }
}
