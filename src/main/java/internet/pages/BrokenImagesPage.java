package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrokenImagesPage extends BasePage {
    public BrokenImagesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "img")
    List<WebElement> allImages;

    public BrokenImagesPage checkAllBrokenLinksImages() {
        System.out.println("All found <img> on the page: [" + allImages.size() + "]");
        for (WebElement image : allImages) {
            String imageURL = image.getAttribute("src");
            String imageALT = image.getAttribute("alt");
            if (imageURL != null) {
                verifyLink(imageALT,imageURL);
            }
        }
        assertAll();
        return this;
    }
}
