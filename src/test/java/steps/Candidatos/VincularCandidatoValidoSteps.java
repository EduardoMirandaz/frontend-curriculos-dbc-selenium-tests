package steps.Candidatos;

import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.CandidatoPopUp;
import pages.DashboardPage;
import pages.UsuarioPages.LoginPage;
import pages.Toasts;
import steps.Cadastro.CadastroValidoSteps;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

public class VincularCandidatoValidoSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();
    Toasts toasts = new Toasts();

    @Test
    public void vincularCandidatoValidoSteps() {

        cadastrarELogar();

        Integer indexPessoaVinculada = vincularCandidatoValido();


        /****
         *  Para validar que um candidato foi de fato vinculado, verifico se no botao
         *  do mesmo está aparecendo a mensagem "Desvincular", o que siginifica que ele
         *  já está vinculado aquela vaga.
         */
        BaseTest.waitSeconds(5);


        String mensagemBotaoVinculado =
                BaseTest.retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(indexPessoaVinculada);


        Assert.assertEquals(mensagemBotaoVinculado, "Desvincular");


    }


    private Integer vincularCandidatoValido() {

        dashboardPage.clicarBtnVincularCandidato();
        return candidatoPopUp.clicarBtnVincular();


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


