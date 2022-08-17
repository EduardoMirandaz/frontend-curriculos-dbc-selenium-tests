package steps.Login;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.JsonManipulation;

public class LogInValidoSteps extends Browser {


    static LoginPage loginPage = new LoginPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void logarUsuarioValido(){
        cadastroValidoSteps.cadastrar();

        logar();

        // Validação

        Assert.assertEquals(loginPage.recuperarToastRegistro(), "deslogar");


    }

    public static void logar() {
        loginPage.preencherEmail(JsonManipulation.recuperarCadastro().get("email"));
        loginPage.preencherPassword(JsonManipulation.recuperarCadastro().get("password"));
        loginPage.clicarBtnLogin();
    }


}
