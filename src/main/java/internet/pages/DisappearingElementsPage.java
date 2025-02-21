package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class DisappearingElementsPage extends BasePage {
    public DisappearingElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DisappearingElementsPage checkDisappearingElement(String elementToFind) {
        WebElement element = driver.findElement(By.xpath("//a[normalize-space(text())='" + elementToFind + "']"));
        isElementPresent(element);
        return this;
    }

    public DisappearingElementsPage checkStyleButton(String button, String expectedFontColor, String expectedFontSize, String expectedButtonColor) {
        WebElement homeButton = driver.findElement(By.xpath("//a[.='" + button + "']"));
        SoftAssert softAssert = new SoftAssert();

        // Найдём стили ДО наведения мышкой
        String actualFontColor = convertRgbToHex(homeButton.getCssValue("color")); // "rgba(218, 75, 75, 1)" - "#DA4B4B"
        String actualFontSize = homeButton.getCssValue("font-size");
        String actualButtonColor = convertRgbToHex(homeButton.getCssValue("background-color"));

        // Распечатаем актуальные значения стилей
        System.out.println("Цвет текста: " + actualFontColor + " (ожидалось: " + expectedFontColor + ")");
        System.out.println("Размер текста: " + actualFontSize + " (ожидалось: " + expectedFontSize + ")");
        System.out.println("Цвет кнопки: " + actualButtonColor + " (ожидалось: " + expectedButtonColor + ")");

        // Проверки
        softAssert.assertEquals(actualFontColor,expectedFontColor.toUpperCase(), "❌ Цвет шрифта не совпадает");
        softAssert.assertEquals(actualFontSize,expectedFontSize, "❌ Размер шрифта не совпадает");
        softAssert.assertEquals(actualButtonColor,expectedButtonColor.toUpperCase(), "❌ Цвет кнопки не совпадает");

        softAssert.assertAll();
        return this;
    }
    private String convertRgbToHex(String rgbValue) {
        // Пример входного значения:
        // "rgba(218, 75, 75, 1)" -->> "#DA4B4B"
        if (rgbValue.startsWith("rgba") || rgbValue.startsWith("rgb")) {
            rgbValue = rgbValue.replace("rgba(", "").replace("rgb(", "").replace(")", "");
            String[] values = rgbValue.split(",");
            int r = Integer.parseInt(values[0].trim());
            int g = Integer.parseInt(values[1].trim());
            int b = Integer.parseInt(values[2].trim());
            return String.format("#%02X%02X%02X", r, g, b);
        }
        return rgbValue; // Если уже в hex-формате
    }

    public DisappearingElementsPage hoverMouseOnButton(String button) {
        WebElement element = driver.findElement(By.xpath("//a[.='" + button + "']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Кусрос успешно наведён на элемент: [" + element.getText() +"]");
        return this;
    }
}
