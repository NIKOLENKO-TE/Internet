package internet.pages;

import internet.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class HorizontalSliderPage extends BasePage {
    public HorizontalSliderPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//input[@type='range']")
    WebElement sliderContainer;

    @FindBy(xpath = "//span[@id='range']")
    WebElement sliderValue;

    public HorizontalSliderPage moveSlider(Float targetValue) {
        System.out.println("Target value to move slider: [" + targetValue + "]");
        float min = Float.parseFloat(Objects.requireNonNull(sliderContainer.getDomAttribute("min"))); // 0
        float max = Float.parseFloat(Objects.requireNonNull(sliderContainer.getDomProperty("max"))); // 5
        float step = Float.parseFloat(Objects.requireNonNull(sliderContainer.getDomProperty("step"))); // 0,5
        float currentValue = 0.0F; // Текущее значение слайдера
        float finalValue = 0.0F; // Финальное значение слайдера
        int steps = 0; // Сколько шагов делать

        // Проверка на диапазон
        if (targetValue == null || targetValue < min || targetValue > max) {
            throw new IllegalArgumentException(
                    String.format("❌ Invalid target value: %.1f. Expected range: [%.1f to %.1f]", targetValue, min, max)
            );
        }

        // Проверка на кратность 0,5
        // Если остаток от деления не равен 0, то считается кратным 0,5
        if (targetValue % step != 0) {
            throw new IllegalArgumentException(
                    String.format("❌ Invalid target value: %.1f. Value must be a multiple of 0.5 ", targetValue)
            );
        }

        // Если значения равны - идём дальше ничего не предпринимая
        if (currentValue == targetValue) {
            return this;
        }

        System.out.println("Min value: [" + min + "]");
        System.out.println("Max value: [" + max + "]");

        // Текущее значение слайдера ДО клика по нему
        currentValue = Float.parseFloat(sliderValue.getText().trim());
        System.out.println("Current value before click: [" + currentValue + "]");

        click(sliderContainer);

        // Текущее значение слайдера ПОСЛЕ клика по нему
        currentValue = Float.parseFloat(sliderValue.getText().trim());
        System.out.println("Current value after click: [" + currentValue + "]");

        // Вычисляем количество шагов
        steps = Math.round((targetValue - currentValue) / step);
        System.out.println("Steps to move slider [" + steps + "] steps " + ((steps < 0) ? "left" : "right"));


        // Двигаем слайдер вправо/влево стрелками клавиатуры
        try {
            Robot robot = new Robot();
            int key = steps > 0 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT;
            for (int i = 0; i < Math.abs(steps); i++) {
                robot.keyPress(key);
                robot.keyRelease(key);
                robot.delay(100);
            }

            finalValue = Float.parseFloat(sliderValue.getText().trim());
            System.out.println("Final value: [" + finalValue + "]");
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
