package internet;

import internet.core.TestBase;
import internet.pages.FileUploaderPage;
import internet.pages.FloatingMenuPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class FloatingMenuTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/floating_menu");
    }

    @Test
    public void floatingMenuPositiveTest(){
        new FloatingMenuPage(app.driver,app.wait)
                //.scrollToCenterPage()
                .scrollToEndOfPage()
                .verifyFloatingMenuIsPresent()
                ;
    }
}
