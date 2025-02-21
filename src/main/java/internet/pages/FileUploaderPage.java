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
            // Переменная хранит строку для вставки в буфер обмена
            StringSelection buffer = new StringSelection(filePath);
            System.out.println(filePath);
            System.out.println(buffer);
            // `buffer` устанавливаем в память компьютера в буфер
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(buffer,null);
            Robot robot = new Robot();
            // Command + Tab для переключения на окно загрузки файла у Mac
            if (System.getProperty("os.name").contains("Mac")) {
                Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to keystroke \"G\" using {command down, shift down}'");
                Thread.sleep(500);

                Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to keystroke \"V\" using {command down}'");
                Thread.sleep(200);

                Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to keystroke return'");
                Thread.sleep(500);

                Runtime.getRuntime().exec("osascript -e 'tell application \"System Events\" to keystroke return'");
            }

            // Ctrl + V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            // Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException | InterruptedException | IOException e) {
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
