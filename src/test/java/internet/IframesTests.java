package internet;

import internet.core.TestBase;
import internet.pages.FramesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class IframesTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/nested_frames");
    }

    @Test
    public void iframePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .getListOfFrames();
    }

    @Test
    public void switchToIframeByIndexPositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToIframeByIndex(1)
                .verifyIframeText("BOTTOM")
                ;
    }

    @Test
    public void switchToIframeByNamePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToIframeByName("frame-bottom")
                .verifyIframeText("BOTTOM")
        ;
    }

    @Test
    public void switchToLeftIframeByNamePositiveTest() {
        new FramesPage(app.driver, app.wait)
                .switchToIframeByName("frame-top")
                .switchToIframeByName("frame-left")
                .verifyIframeText("LEFT")
                .stepUp()
                .switchToIframeByName("frame-middle")
                .verifyIframeText("MIDDLE")
                .stepUp()
                .switchToIframeByName("frame-right")
                .verifyIframeText("RIGHT")
                .exitFromAllFrames()
                .switchToIframeByName("frame-bottom")
                .verifyIframeText("BOTTOM")
        ;
    }
}
