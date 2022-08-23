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

public class AdicionarCandidatoCpfPequenoSteps extends Browser {

    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();

    @Test
    public void adicionarCandidatoCpfPequenoSteps() {
        /**
         *  Tentando adicionar candidato com CPF de tamanho válido inferior
         */
        cadastrarELogar();

        adicionarCandidatoCpfPequeno();


        BaseTest.waitSeconds(2);

        /**
         *
         * Verificando que permaneci na mesma página e que a mensagem de erro no cpf apareceu.
         */

        Assert.assertEquals(registroDadosPessoaisCandidatoPage.recuperarMsgErroCpf(), "- CURTO DEMAIS.");


    }

    public static void adicionarCandidatoCpfPequeno() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato(InvalidacoesCandidato.CPF_ABAIXO_TAMANHO_MINIMO);
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


