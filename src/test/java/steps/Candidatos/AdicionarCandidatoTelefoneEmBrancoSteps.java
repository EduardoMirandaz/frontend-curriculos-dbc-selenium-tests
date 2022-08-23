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

public class AdicionarCandidatoTelefoneEmBrancoSteps extends Browser {

    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();

    @Test
    public void adicionarCandidatoTelefoneEmBrancoSteps() {
        /**
         *  Tentando adicionar candidato com telefone em branco.
         */
        cadastrarELogar();

        adicionarCandidatoTelefoneEmBranco();

        BaseTest.waitSeconds(2);

        /**
         *
         * Verificando que permaneci na mesma página e que a mensagem de erro no telefone apareceu.
         */

        Assert.assertEquals(registroDadosPessoaisCandidatoPage.recuperarMsgErroTelefone(), "- OBRIGATÓRIO");


    }

    public static void adicionarCandidatoTelefoneEmBranco() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato(InvalidacoesCandidato.TELEFONE_EM_BRANCO);
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPagina(candidatoCriado);
        registroDadosPessoaisCandidatoPage.popularCamposEscritosTelefoneEmBranco(dadosPessoais);
        registroDadosPessoaisCandidatoPage.adicionarArquivo(curriculoValidoPath);
        registroDadosPessoaisCandidatoPage.clicarBotaoProximo();

    }

    private void cadastrarELogar() {
        cadastrar();
        logar();
    }

}


