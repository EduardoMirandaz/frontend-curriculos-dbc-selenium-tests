package steps.Login;

import org.junit.Assert;
import org.junit.Test;
import pages.DashboardPage;
import pages.UsuarioPages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.JsonManipulation;

import static util.BaseTest.waitSeconds;

public class LogInValidoSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void logarUsuarioValido(){
        cadastroValidoSteps.cadastrar();

        logar();

        // Validação

        Assert.assertEquals(dashboardPage.recuperarMensagemListaDeVagas(), "Lista de vagas");


    }

    public static void logar() {
        loginPage.preencherEmail(JsonManipulation.recuperarCadastro().get("email"));
        loginPage.preencherPassword(JsonManipulation.recuperarCadastro().get("senha"));
        waitSeconds(3);
        loginPage.clicarBtnLogin();
    }


}
