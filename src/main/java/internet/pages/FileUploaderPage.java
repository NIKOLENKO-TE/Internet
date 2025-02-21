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

            if (System.getProperty("os.name").contains("Mac")) {
                // Получаем PID окна загрузки
                Process process = Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"Google Chrome\" to activate"
                });

                robot.delay(1000);

                // Получаем список активных окон и ищем окно загрузки
                Process getWindowProcess = Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to get name of every process whose frontmost is true"
                });

                robot.delay(500);

                // Переключаем фокус на окно загрузки файла
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke tab using command down"
                });

                robot.delay(1000);

                // Вставляем путь к файлу
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke \"V\" using command down"
                });

                robot.delay(500);

                // Подтверждаем выбор
                Runtime.getRuntime().exec(new String[]{
                        "osascript", "-e",
                        "tell application \"System Events\" to keystroke return"
                });

                robot.delay(500);

                // Закрываем окно загрузки файла
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
