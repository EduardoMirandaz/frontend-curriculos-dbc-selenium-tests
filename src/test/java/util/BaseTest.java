package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;
import java.util.UUID;

public class BaseTest extends Elements{

    public static void click (By by){
        waitElement(by);
        element(by).click();
    }

    public static String getText (By by){
        try{
            waitElement(by);
            return element(by).getText();
        }catch(NoSuchElementException | TimeoutException ns){
            Browser.refresh();
        }
        return null;
    }

    public static void sendKeys (By by, String text){
        try{
            waitElement(by);
            element(by).sendKeys(text);
        }catch(NoSuchElementException | TimeoutException ns){
            Browser.refresh();
        }

    }

    public static void sendKeys (By by, Keys key){
        try{
            waitElement(by);
            element(by).sendKeys(key);
        }catch(NoSuchElementException | TimeoutException ns){
            Browser.refresh();
        }

    }

    public static void hover(By by){
        waitElement(by);
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(by)).perform();
    }


    public String gerarRandomico(Integer tamanho){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,tamanho);
    }



}
