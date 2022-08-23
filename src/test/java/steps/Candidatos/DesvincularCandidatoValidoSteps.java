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
import static steps.Candidatos.VincularCandidatoValidoSteps.vincularCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;
import static util.BaseTest.waitSeconds;

public class DesvincularCandidatoValidoSteps extends Browser {
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();

    CandidatosPage candidatosPage = new CandidatosPage();

    @Test
    public void desvincularCandidatoValidoSteps() {
        /**
         * Antes de tudo, cadastro, faço login e vinculo uma pessoa à uma vaga
         */
        cadastrarELogar();

        /**
         * Adiciono um candidato valido para pelo menos ter um a realizar as operacoes de vaga.
         */
        JSONObject candidatoValidoAdicionado = adicionarCandidatoValido();
        waitSeconds(6);
        dashboardPage.clicarBtnDashboard();

        Integer indexPessoaVinculada = vincularCandidatoValido();
        waitSeconds(7);
        /**
         * Verifico se consegui vincular, para depois poder desvincular;
         */
        String mensagemBotaoVinculado1 =
                BaseTest.retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(indexPessoaVinculada);
        Assert.assertEquals(mensagemBotaoVinculado1, "Desvincular");

        /**
         * Desvinculo o candidato.
         */
        desvincularCandidatoValido(indexPessoaVinculada);

        /****
         *  Para validar que um candidato foi de fato desvinculado, verifico se no botao
         *  do mesmo está aparecendo a mensagem "Vincular", o que siginifica que ele
         *  foi desvinculado daquela vaga. Isso porque, nos passos acima, validei que ele
         *  estava vinculado a vaga daquele index.
         */
        waitSeconds(5);

        String mensagemBotaoVinculado2 =
                BaseTest.retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(indexPessoaVinculada);

        /**
         * Verifico que a mensagem do botao virou vincular, ou seja, o candidato foi desvinculado;
         */
        Assert.assertEquals(mensagemBotaoVinculado2, "Vincular");

        /**
         * Deletando candidato após criação válida.
         */
        candidatoPopUp.clicarBtnFechar();
        waitSeconds(2);
        dashboardPage.clicarBtnCandidatos();
        int posicao = candidatosPage.buscarCandidatoPorNome(candidatoValidoAdicionado.get("nome").toString());
        deletarCandidatoValido(posicao);

    }

    private void desvincularCandidatoValido(Integer posicaoCandidatoVinculado) {
        candidatoPopUp.clicarBtnDesvincular(posicaoCandidatoVinculado);
    }

    private void cadastrarELogar() {
        cadastrar();
        logar();
    }



}


