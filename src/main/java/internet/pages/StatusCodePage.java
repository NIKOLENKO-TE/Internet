package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StatusCodePage extends BasePage {
    public StatusCodePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;

    // Переменная хранит счётчик ссылок
    private int linkCounter = 0;

    public StatusCodePage checkAllURL() {
        System.out.println("🔗 Общее количество ссылок на странице: [" + allLinks.size() + "]");
        for (WebElement link : allLinks) {
            linkCounter++;
            String urlText = link.getText().trim(); // Хранит текст ссылки
            String href = link.getAttribute("href"); // Хранит саму ссылку
            System.out.println("🔗 Link " + linkCounter + ": [" + (!urlText.isEmpty() ? urlText : (href != null && !href.isEmpty() ? href : "null")) + "], URL: [" + (href != null ? href : "null") + "]");
        }
        return this;
    }

    public StatusCodePage checkBrokenLinks() {
        System.out.println("🔍 Общее количество ссылок на странице: [" + allLinks.size() + "]");
        for (WebElement link : allLinks) {
            String urlText = link.getText().trim(); // Хранит текст ссылки
            String linkURL = link.getAttribute("href"); // Хранит саму ссылку
            verifyLink(urlText, linkURL);
        }
        assertAll();
        return this;
    }

}
