package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FramesPage extends BasePage {
    public FramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(tagName = "frame")
    List<WebElement> iframes;

    public FramesPage getListOfFrames() {
        System.out.println("Number of iFrames on the page are: [" + iframes.size() + "]");

        for(WebElement iframe : iframes){
            String iFrameNAME = iframe.getAttribute("name");
            String iFrameSRC = iframe.getAttribute("src");
            System.out.println("Iframe found NAME: [" + (iFrameNAME != null ? iFrameNAME : "No ID") + "], SRC: [" + (iFrameSRC != null ? iFrameSRC : "No SRC") + "]");
        }
        return this;
    }

    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(xpath = "/html/body")
    WebElement bodyLocator;

    public FramesPage verifyIframeText(String text) {
        shouldHaveText(bodyLocator,text, 3000);
        return this;
    }

    public FramesPage switchToIframeByName(String name) {
        //scrollTo(500);
        driver.switchTo().frame(name);
        return this;
    }

    public FramesPage stepUp() {
        driver.switchTo().parentFrame();
        return this;
    }

    public FramesPage exitFromAllFrames() {
        driver.switchTo().defaultContent();
        return this;
    }
}
