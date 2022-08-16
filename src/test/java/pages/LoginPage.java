package pages;

import org.openqa.selenium.By;
import util.BaseTest;
import util.JsonManipulation;

public class LoginPage extends BaseTest {

    public static final By fieldEmail = By.cssSelector("#email");
    public static final By fieldPassword = By.cssSelector("#passwd");
    public static final By msgEntreComSuaConta = By.cssSelector("#root > div:nth-child(1) > div > div.sc-crXcEl.bTJbnQ > div > h2");

    public static final By btnLogin = By.cssSelector("#root > div:nth-child(1) > div > div.sc-crXcEl.bTJbnQ > div > form > button");

    public static final By btnRegistrar = By.cssSelector("#root > div:nth-child(1) > div > div.sc-iqcoie.iFxVOA > button");
    public void preencherEmail(){
        BaseTest.sendKeys(fieldEmail, JsonManipulation.recuperarCadastro().get("email"));
    }
    public void preencherPassword(){
        BaseTest.sendKeys(fieldPassword, JsonManipulation.recuperarCadastro().get("password"));
    }

    public void clicarBtnLogin(){
        BaseTest.click(btnLogin);
    }

    public void clicarBtnRegistrar(){
        BaseTest.click(btnRegistrar);
    }


    public String retornoLogin(){
        return BaseTest.getText(msgEntreComSuaConta);
    }


}
