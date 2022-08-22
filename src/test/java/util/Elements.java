package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class Elements extends Browser{


    // método para pegar um elemento
    public static WebElement element(By modoDeBusca){
        try {
            return driver.findElement(modoDeBusca);
        }catch (NoSuchElementException ns){
            ns.printStackTrace();
        }
        return null;
    }

    // método para esperar um elemento
    public static void waitElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


}
