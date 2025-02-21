package internet;

import internet.core.TestBase;
import internet.pages.DragAndDropPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class DragAndDropTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/drag_and_drop");
    }

    @Test
    public void dragAndDropPositiveTest(){
        new DragAndDropPage(app.driver, app.wait)
                .actionDragMe()
                .verifyText("a")
                ;
    }
}
