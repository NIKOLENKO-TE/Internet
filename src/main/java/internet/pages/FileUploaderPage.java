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
            // Устанавливаем путь к файлу в буфер обмена
            StringSelection buffer = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(buffer, null);

            Robot robot = new Robot();

            // Command + Tab для переключения на окно загрузки файла на Mac
            if (System.getProperty("os.name").contains("Mac")) {
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_META);

                // Задержка, чтобы дать окну загрузки активироваться
                robot.delay(1000);

                // Выполняем AppleScript для фокусировки на окне загрузки файлов
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke \"G\" using {command down, shift down}"
                });

                robot.delay(500);

                // Вставка пути из буфера
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke \"V\" using {command down}"
                });

                robot.delay(200);

                // Нажатие Enter для подтверждения пути
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke return"
                });

                robot.delay(500);

                // Повторное нажатие Enter для загрузки файла
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke return"
                });
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
