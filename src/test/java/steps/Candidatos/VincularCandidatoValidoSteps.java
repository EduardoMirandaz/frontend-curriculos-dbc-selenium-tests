package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.CandidatoPopUp;
import pages.CandidatosPages.CandidatosPage;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.adicionarCandidatoValido;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.dashboardPage;
import static steps.Candidatos.DeletarCandidatoValidoSteps.deletarCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;
import static util.BaseTest.waitSeconds;

public class VincularCandidatoValidoSteps extends Browser {
    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatoPopUp candidatoPopUp = new CandidatoPopUp();
    CandidatosPage candidatosPage = new CandidatosPage();

    @Test
    public void vincularCandidatoValidoSteps() {
        /**
         * Antes de tudo, cadastro, faço login e vinculo uma pessoa à uma vaga
         */
        cadastrarELogar();

        /**
         * Adiciono um candidato valido para pelo menos ter um a realizar as operacoes de vaga.
         */
        JSONObject candidatoValidoAdicionado = adicionarCandidatoValido();
        waitSeconds(5);
        dashboardPage.clicarBtnDashboard();

        Integer indexPessoaVinculada = vincularCandidatoValido();

        /****
         *  Para validar que um candidato foi de fato vinculado, verifico se no botao
         *  do mesmo está aparecendo a mensagem "Desvincular", o que siginifica que ele
         *  já está vinculado aquela vaga.
         */
        waitSeconds(5);

        String mensagemBotaoVinculado =
                BaseTest.retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(indexPessoaVinculada);

        /**
         * Verifico que a mensagem do botao virou desvincular, ou seja, o candidato foi vinculado;
         */
        Assert.assertEquals(mensagemBotaoVinculado, "Desvincular");


        /**
         * Deletando candidato após criação válida.
         */
        candidatoPopUp.clicarBtnFechar();
        waitSeconds(2);
        dashboardPage.clicarBtnCandidatos();
        int posicao = candidatosPage.buscarCandidatoPorNome(candidatoValidoAdicionado.get("nome").toString());
        deletarCandidatoValido(posicao);


    }

    public static Integer vincularCandidatoValido() {
        dashboardPage.clicarBtnDashboard();
        waitSeconds(2);
        dashboardPage.clicarBtnVincularCandidato();
        return candidatoPopUp.clicarBtnVincular();

    }

    private void cadastrarELogar() {
        cadastrar();
        waitSeconds(4);
        logar();
    }



}


