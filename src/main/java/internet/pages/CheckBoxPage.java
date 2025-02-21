package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class CheckBoxPage extends BasePage {
    public CheckBoxPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//form[@id='checkboxes']/input")
    List<WebElement> checkBoxes;

    public CheckBoxPage printCheckBox() {
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            System.out.println("Чекбокс текст: " + labelText);
        }
        return this;
    }

    private String chooseCheckBoxByName(WebElement checkBox) {
        return (String) js.executeScript("return arguments[0].nextSibling.textContent;", checkBox);
    }

    public CheckBoxPage selectCheckboxByText(String checkBoxText) {
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    checkBox.click();
                } else if (checkBox.isSelected()) {
                    System.out.println("✅ Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }

    public CheckBoxPage verifyCheckbox(String checkBoxText) {
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                Assert.assertTrue(checkBox.isSelected());
                return this;
            }
        }
        return this;
    }

    public CheckBoxPage selectCheckboxByTextWithActions(String checkBoxText) {
        Actions actions = new Actions(driver);
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    actions.moveToElement(checkBox).click().perform();
                    System.out.println("✅ Checkbox selected: [" + labelText.trim() + "]");
                } else if (checkBox.isSelected()) {
                    System.out.println("✅ Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }

    public CheckBoxPage selectCheckboxByTextWithRobot(String checkBoxText) {
        String labelText;
        for (WebElement checkBox : checkBoxes) {
            labelText = chooseCheckBoxByName(checkBox);
            if (labelText.trim().equals(checkBoxText)) {
                if (!checkBox.isSelected()) {
                    try {
                        Robot robot = new Robot();
                        Actions actions = new Actions(driver);
                        actions.moveToElement(checkBox).perform();
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                        robot.delay(100);
                        System.out.println("✅ Checkbox selected: [" + labelText.trim() + "]");
                    } catch (AWTException e) {
                        Assert.fail("❌ Error when working with checkbox: " + checkBoxText);
                    }
                } else if (checkBox.isSelected()) {
                    System.out.println("❌ Checkbox already selected");
                }
                return this;
            }
        }
        return this;
    }
}
