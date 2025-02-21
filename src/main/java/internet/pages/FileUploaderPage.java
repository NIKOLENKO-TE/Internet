package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class FileUploaderPage extends BasePage {
    public FileUploaderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "file-upload")
    WebElement file_upload;

    public FileUploaderPage chooseFileByChooseFileButton(String filePath) {
        //type(file_upload,filePath);
        file_upload.sendKeys(filePath);
        return this;
    }

    @FindBy(id = "file-submit")
    WebElement file_submit;

    public FileUploaderPage clickOnUploadButton() {
        click(file_submit);
        return this;
    }


    @FindBy(id = "uploaded-files")
    WebElement uploaded_files;

    public FileUploaderPage verifyFileName(String expectedFileName) {
        shouldHaveText(uploaded_files, expectedFileName, 5000);
        return this;
    }


    //* **************************
    @FindBy(id = "drag-drop-upload")
    WebElement boxContainer;

    public FileUploaderPage chooseFileInBox(String filePath) {
        click(boxContainer);
        try {
            // Устанавливаем путь в буфер обмена
            StringSelection buffer = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(buffer, null);

            Robot robot = new Robot();
            robot.delay(1000); // Даем время окну загрузки открыться

            if (System.getProperty("os.name").contains("Mac")) {
                // Переключаем фокус на браузер (уже работает)
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"Google Chrome\" to activate"
                });

                robot.delay(1000);

                // Теперь используем Robot для вставки пути

                // ⌘ (Cmd) + V для вставки пути
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);

                robot.delay(500);

                // Enter для подтверждения пути
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                robot.delay(500);

                // Еще раз Enter, чтобы загрузить файл
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }

        } catch (AWTException | IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }



    @FindBy(className = "dz-filename")
    WebElement dz_filename;

    public FileUploaderPage verifyFileNameInBox(String fileName) {
        shouldHaveText(dz_filename, fileName, 5000);
        return this;
    }
}
