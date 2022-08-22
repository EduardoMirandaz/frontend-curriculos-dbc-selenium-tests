package steps.Listagem;

import org.junit.Assert;
import org.junit.Test;
import pages.DashboardPage;
import pages.UsuarioPages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.JsonManipulation;

public class ListarVagasAposTelaDeCandidatosSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void listarVagasAposTelaDeCandidatosSteps(){

        cadastrarELogar();

        dashboardPage.clicarBtnCandidatos();
        dashboardPage.clicarBtnDashboard();


        // Validação
        Assert.assertEquals(dashboardPage.recuperarMensagemListaDeVagas(), "Lista de vagas");


    }

    private void cadastrarELogar() {
        cadastroValidoSteps.cadastrar();
        logar();
    }

    public static void logar() {
        loginPage.preencherEmail(JsonManipulation.recuperarCadastro().get("email"));
        loginPage.preencherPassword(JsonManipulation.recuperarCadastro().get("senha"));
        loginPage.clicarBtnLogin();
    }


}


