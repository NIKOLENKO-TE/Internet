package internet;

import internet.core.TestBase;
import internet.pages.FileUploaderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static internet.pages.HomePage.HOME_PAGE_URL;

public class FileUploaderTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/upload");
    }

    @Test
    public void  fileUploaderPositiveTest(){
        new FileUploaderPage(app.driver,app.wait)
                .chooseFileByChooseFileButton("C:\\Users\\PORTISHEAD\\Downloads\\unnamed2.png")
                .clickOnUploadButton()
                .verifyFileName("unnamed2.png")
        ;
    }

    @Test
    public void  fileUploaderInBoxPositiveTest(){
        new FileUploaderPage(app.driver,app.wait)
                .chooseFileInBox("/Users/portishead-macbook/Downloads/cats.jpeg")
                .verifyFileNameInBox("cats.jpeg")
        ;
    }
}
