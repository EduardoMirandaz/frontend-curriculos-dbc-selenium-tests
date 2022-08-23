package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.*;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;
import util.Enums.InvalidacoesCandidato;
import util.JsonManipulation;

import java.util.ArrayList;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Login.LogInValidoSteps.logar;
import static util.Paths.curriculoValidoPath;

public class AdicionarCandidatoDOBEmBrancoSteps extends Browser {

    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    @Test
    public void adicionarCandidatoDOBEmBrancoSteps() {
        /**
         *  Tentando adicionar candidato com data de nascimento em branco.
         */
        cadastrarELogar();

        adicionarCandidatoDoBEmBranco();

        BaseTest.waitSeconds(2);

        /**
         *
         * Verificando que permaneci na mesma página e que a mensagem de erro na data de nascimento apareceu.
         */

        Assert.assertEquals(registroDadosPessoaisCandidatoPage.recuperarMsgErroDOB(), "- OBRIGATÓRIO");


    }

    public static void adicionarCandidatoDoBEmBranco() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato(InvalidacoesCandidato.DOB_EM_BRANCO);
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


