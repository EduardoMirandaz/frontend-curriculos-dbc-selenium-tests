package steps.Cadastro;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import pages.Toasts;
import steps.Login.LogInValidoSteps;
import util.Browser;
import util.Geradores;
import util.JsonManipulation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static steps.Login.LogInValidoSteps.logar;
import static util.Elements.waitElement;

public class CadastroValidoSteps extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();
    Geradores geradores = new Geradores();
    @Test
    public void cadastrarUsuarioValido(){

        cadastrar();

        // Validação
        /*********************************************************
         Para validar se o registro foi efetuado de maneira idonea
         faço login e verifico se o botao de deslogar está disponível.
         */


        logar();

        Assert.assertEquals(loginPage.recuperarToastRegistro(), "deslogar");

    }


    public void cadastrar(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail(geradores.gerarLoginRandomico());
        String password = registerPage.preencherSenha(geradores.gerarSenhaRandomica());
        registerPage.preencherConfirmarSenha(password);

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("password", password);
        JsonManipulation.criarJsonCadastro(login);

//         Clicar no botao submit
        registerPage.clicarBtnRegistrarSe();

    }


}
