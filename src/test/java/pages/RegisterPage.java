package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import util.BaseTest;
import util.Geradores;
import util.JsonManipulation;

import java.util.Map;

// Mapeamentos

public class RegisterPage extends BaseTest {

    private static final By btnRegistrarSe = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/button");
    private final By fieldEmail = By.cssSelector("#email");
    private final By fieldSenha = By.cssSelector("#senha");
    private final By fieldConfirmarSenha = By.cssSelector("#confirmaSenha");
    public final By msgRegistreUmaConta = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/h2");

    public final By msgEmailInvalido = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/div[1]/span");
    public final By msgSenhaInvalida = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/div[2]/span");
    public final By msgConfirmarSenhaInvalida = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/form/div[3]/span");
    // CLICKS
    public void clicarBtnRegistrarSe(){
        BaseTest.click(btnRegistrarSe);
    }

    // SEND KEYS
    public String preencherEmail(String email) {
        BaseTest.sendKeys(fieldEmail, email);
        return email;
    }

    public String preencherSenha(String senha){
        BaseTest.sendKeys(fieldSenha, senha);
        return senha;
    }

    public String preencherSenha(){
        Geradores geradores = new Geradores();
        String senhaGerada = geradores.gerarSenhaRandomica();
        BaseTest.sendKeys(fieldSenha, senhaGerada);
        return senhaGerada;
    }

    public String preencherEmail(){
        Geradores geradores = new Geradores();
        String emailGerado = geradores.gerarLoginRandomico();
        BaseTest.sendKeys(fieldEmail, emailGerado);
        return emailGerado;
    }

    public String preencherConfirmarSenha(String senha){
        BaseTest.sendKeys(fieldConfirmarSenha, senha);
        return senha;
    }

    public String recuperarMensagemEmailInvalido(){
        return BaseTest.getText(msgEmailInvalido);
    }
    public String recuperarMensagemSenhaInvalida(){
        return BaseTest.getText(msgSenhaInvalida);
    }
    public String recuperarMensagemConfirmarSenhaValida(){
        return BaseTest.getText(msgConfirmarSenhaInvalida);
    }

    public String retornoRegistrar(){
        return BaseTest.getText(msgRegistreUmaConta);
    }

}
