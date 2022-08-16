package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Elements extends Browser{


    // método para pegar um elemento
    protected static WebElement element (By modoDeBusca){
        return driver.findElement(modoDeBusca);
    }

    // método para esperar um elemento
    public static void waitElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


}
