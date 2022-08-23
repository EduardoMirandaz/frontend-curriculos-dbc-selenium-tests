package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.CandidatosPage;
import pages.CandidatosPages.RegistroDadosPessoaisCandidatoPage;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;
import util.Enums.InvalidacoesCandidato;
import util.JsonManipulation;

import java.util.ArrayList;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Login.LogInValidoSteps.logar;
import static util.Paths.curriculoValidoPath;

public class AdicionarCandidatoCpfEmBrancoSteps extends Browser {

    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();

    @Test
    public void adicionarCandidatoCpfEmBrancoSteps() {
        /**
         *  Tentando adicionar candidato com CPF em branco
         */
        cadastrarELogar();

        adicionarCandidatoCpfEmBranco();

        BaseTest.waitSeconds(2);

        /**
         *
         * Verificando que permaneci na mesma página e que a mensagem de erro no cpf apareceu.
         */

        Assert.assertEquals(registroDadosPessoaisCandidatoPage.recuperarMsgErroCpf(), "- OBRIGATÓRIO");


    }

    public static void adicionarCandidatoCpfEmBranco() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato(InvalidacoesCandidato.CPF_EM_BRANCO);
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPagina(candidatoCriado);
        registroDadosPessoaisCandidatoPage.popularCamposEscritos(dadosPessoais);
        registroDadosPessoaisCandidatoPage.adicionarArquivo(curriculoValidoPath);
        registroDadosPessoaisCandidatoPage.clicarBotaoProximo();

    }

    private void cadastrarELogar() {
        cadastrar();
        logar();
    }

}


