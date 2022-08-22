package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

import static pages.CandidatosPages.CandidatoPopUp.*;
import static pages.CandidatosPages.CandidatoPopUp.pos;
import static pages.CandidatosPages.CandidatosPage.*;

public class BaseTest extends Elements{

    public static void click (By by){
        waitElement(by);
        element(by).click();
    }

    public static void clickVincularDesvincular(By vincular, By desvincular, String div){

        if(element(By.cssSelector(div)).getText().equals("Vincular")){
            click(vincular);
        }
        else{
            click(desvincular);
        }

    }

    public static By buscarBotaoVincular(String strBtnVincularDesvincularInicio, String strBtnVincularDesvincularFinal, Integer pos) {
        if(element(By.cssSelector(strBtnVincularDesvincularInicio + pos + strBtnVincularDesvincularFinal)).getText().equals("Vincular")){
            return By.cssSelector(strBtnVincularDesvincularInicio + pos + strBtnVincularDesvincularFinal);
        }
        else{
            return buscarBotaoVincular(strBtnVincularDesvincularInicio, strBtnVincularDesvincularFinal, pos+1);
        }
    }

    public static By buscarBotaoDesvincular(String strBtnVincularDesvincularInicio, String strBtnVincularDesvincularFinal, Integer pos) {
        if(element(By.cssSelector(strBtnVincularDesvincularInicio + pos + strBtnVincularDesvincularFinal)).getText().equals("Desvincular")){
            return By.cssSelector(strBtnVincularDesvincularInicio + pos + strBtnVincularDesvincularFinal);
        }
        else{
            return buscarBotaoVincular(strBtnVincularDesvincularInicio, strBtnVincularDesvincularFinal, pos+1);
        }
    }

    public static Integer encontrarIndexPessoaPossivelDeSerVinculada(Integer pos) {
        while(element(By.cssSelector(strBtnVincularDesvincularInicio + pos + strBtnVincularDesvincularFinal)).getText().equals("Desvincular")){
            pos++;
        }
        return pos;
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

    public static String getMsgFromToast(By by){
        return element(by).findElement(by).getText();
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

    public static void sendKeysPage1 (By by, ArrayList<String> keys){
        waitElement(by);
        element(by).sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP), keys.get(0));
        element(by).sendKeys(Keys.TAB, keys.get(1));
        element(by).sendKeys(Keys.TAB, Keys.TAB, keys.get(2));
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, keys.get(3)+"1");
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, keys.get(4));
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        waitSeconds(2);

    }

    public static void sendKeysPage1Edicao(By by, ArrayList<String> keys){
        waitElement(by);
        element(by).sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP), keys.get(0));
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        waitSeconds(2);
    }

    private static void pressTab(By by, Integer i) {
        for(int j = 0; j < i; j++){
            element(by). sendKeys(Keys.TAB);
        }
    }

    public static void hover(By by){
        waitElement(by);
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(by)).perform();
    }

    public static void clicarBotaoVincular(Integer indexPessoaQuePodeSerVinculada) {
        click(By.cssSelector(strBtnVincularDesvincularInicio + indexPessoaQuePodeSerVinculada + strBtnVincularDesvincularFinal));
    }

    public static String retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(Integer indexPessoaVinculada) {
        return BaseTest.getText(By.cssSelector(strBtnVincularDesvincularInicio + indexPessoaVinculada + strBtnVincularDesvincularFinal));
    }

    public static void clicarBotaoProximo(By by) {
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
    }


    public static void preencherNivel(By nivel) {
        element(nivel).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.TAB);
    }

    public static void sendKeysPage3(By by, ArrayList<String> keys) {
        waitElement(by);
        element(by).sendKeys(keys.get(0));
        element(by).sendKeys(Keys.TAB, keys.get(1));
        element(by).sendKeys(Keys.TAB, Keys.TAB, keys.get(2));
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, keys.get(3)+"1");
        waitSeconds(2);
    }

    public static void sendKeysPage4(By by, ArrayList<String> keys) {
        waitElement(by);
        element(by).sendKeys(keys.get(0));
        element(by).sendKeys(Keys.TAB, keys.get(1));
        element(by).sendKeys(Keys.TAB, Keys.TAB, keys.get(2));
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, keys.get(3)+"1");
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, keys.get(4));
        waitSeconds(2);
    }

    public static int buscarCandidatoPorNome(String nomeCandidatoCriado) {
        try{
            int pos = 2;
            while (! nomeCandidatoCriado.contains(element(By.cssSelector(inicioMapeamentoCandidato + pos + finalMapeamentoCandidato)).getText().replace("...", ""))  ) {
                pos++;
                if (pos == 12) {
                    pos--;
                    break;
                }
            }
            if (nomeCandidatoCriado.contains(element(By.cssSelector(inicioMapeamentoCandidato + pos + finalMapeamentoCandidato)).getText().replace("...", ""))) {
                return pos;
            } else {
                BaseTest.click(btnProximaPagina);
                return buscarCandidatoPorNome(nomeCandidatoCriado);
            }
        }catch (NoSuchElementException ns){
            ns.printStackTrace();
        }
        return 0;
    }

    public static void clicarBotaoProximoPosEdicao(By by) {
        element(by).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER);
    }

    public String gerarRandomico(Integer tamanho){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0,tamanho);
    }

    public static void waitSeconds(Integer secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
