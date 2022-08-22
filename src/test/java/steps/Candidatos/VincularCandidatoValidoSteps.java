package steps.Candidatos;

import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.CandidatoPopUp;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Login.LogInValidoSteps.logar;

public class VincularCandidatoValidoSteps extends Browser {
    DashboardPage dashboardPage = new DashboardPage();
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();

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
        cadastrar();
        logar();
    }



}


