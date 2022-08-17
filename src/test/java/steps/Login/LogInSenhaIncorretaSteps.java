package steps.Login;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.JsonManipulation;

public class LogInSenhaIncorretaSteps extends Browser {


    static LoginPage loginPage = new LoginPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void logInSenhaIncorretaSteps(){
        cadastroValidoSteps.cadastrar();

        logar();

        // Validação

        Assert.assertEquals(loginPage.retornoLogin(), "Entre com sua conta");


    }

    public static void logar() {
        loginPage.preencherEmail(JsonManipulation.recuperarCadastro().get("email"));
        loginPage.preencherPassword(JsonManipulation.recuperarCadastro().get("password")+"@");
        loginPage.clicarBtnLogin();
    }


}
