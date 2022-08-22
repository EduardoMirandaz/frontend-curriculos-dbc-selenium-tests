package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.*;
import pages.CandidatosPages.*;
import pages.UsuarioPages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

import java.util.ArrayList;

import static util.Paths.curriculoValidoPath;

public class AdicionarCandidatoValidoSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    static DashboardPage dashboardPage = new DashboardPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    static RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    static RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    static RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();
    @Test
    public void adicionarCandidatoValidoSteps() {

        cadastrarELogar();

        String nomeCandidatoCriado = adicionarCandidatoValido();

        /****
         *  Para validar que um candidato foi de fato vinculado, verifico se no botao
         *  do mesmo está aparecendo a mensagem "Desvincular", o que siginifica que ele
         *  já está vinculado aquela vaga.
         */
        BaseTest.waitSeconds(5);


        Assert.assertTrue(candidatosPage.buscarCandidatoPorNome(nomeCandidatoCriado));


    }

    static String adicionarCandidatoValido() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPagina(candidatoCriado);
        registroDadosPessoaisCandidatoPage.popularCamposEscritos(dadosPessoais);
        registroDadosPessoaisCandidatoPage.adicionarArquivo(curriculoValidoPath);
        registroDadosPessoaisCandidatoPage.clicarBotaoProximo();

        ArrayList<String> endereco = registroEnderecoCandidatoPage.recuperarAtributosEndereco(candidatoCriado);
        registroEnderecoCandidatoPage.popularCamposEscritos(endereco);
        registroEnderecoCandidatoPage.clicarBotaoProximo();

        ArrayList<String> escolaridades = registroEscolaridadeCandidatoPage.recuperarAtributosEscolaridade(candidatoCriado);
        registroEscolaridadeCandidatoPage.preencherNivel();
        registroEscolaridadeCandidatoPage.popularCamposEscritos(escolaridades);
        registroEscolaridadeCandidatoPage.clicarBotaoProximo();

        ArrayList<String> experiencias = registroExperienciasCandidatoPage.recuperarAtributosExperiencias(candidatoCriado);
        registroExperienciasCandidatoPage.popularCamposEscritos(experiencias);
        registroExperienciasCandidatoPage.clicarBotaoProximo();


        return candidatoCriado.get("nome").toString();
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


