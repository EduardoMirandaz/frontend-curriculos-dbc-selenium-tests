package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BaseTest;
import util.JsonManipulation;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BaseTest {

    public static final By fieldEmail = By.cssSelector("#email");
    public static final By fieldPassword = By.cssSelector("#senha");
    public static final By msgEntreComSuaConta = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/h2");
    public static final By msgErroEmail = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/div[1]/span");
    public static final By msgErroSenha = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/div[2]/span");

    public static final By btnLogin = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/button");

    public static final By btnRegistrar = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/button");

    public static final By toastRegistradoComSucesso = By.cssSelector("#root > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)");
    public void preencherEmail(){
        BaseTest.sendKeys(fieldEmail, JsonManipulation.recuperarCadastro().get("email"));
    }

    public String recuperarMsgErroEmail(){
        return BaseTest.getText(msgErroEmail);
    }
    public String recuperarMsgErroSenha(){
        return BaseTest.getText(msgErroSenha);
    }
    public void preencherEmail(String email){
        BaseTest.sendKeys(fieldEmail, email);
    }

    public void preencherPassword(){
        BaseTest.sendKeys(fieldPassword, JsonManipulation.recuperarCadastro().get("password"));
    }

    public void preencherPassword(String email){
        BaseTest.sendKeys(fieldPassword, email);
    }
    public void clicarBtnLogin(){
        BaseTest.click(btnLogin);
    }

    public void clicarBtnRegistrar(){
        BaseTest.click(btnRegistrar);
    }


    public String recuperarToastRegistro(){
        return BaseTest.getText(toastRegistradoComSucesso);
    }


    public String retornoLogin(){
        return BaseTest.getText(msgEntreComSuaConta);
    }


    public void abrirTelaDeRegistro() {
        String url = "https://curriculos-dev.vercel.app/register";

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        driver = new ChromeDriver(); // Instancia e cria um browser
        wait = new WebDriverWait(driver, 30); // delay
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS); // Definir um tempo para iniciar
        driver.manage().window().maximize(); // Maximizar a janela do navegador
    }
}
