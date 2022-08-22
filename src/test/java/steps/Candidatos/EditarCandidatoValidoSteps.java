package steps.Candidatos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.*;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

import java.util.ArrayList;
import java.util.List;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.adicionarCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;
import static util.Paths.curriculoValidoPath;


public class EditarCandidatoValidoSteps extends Browser {

    DashboardPage dashboardPage = new DashboardPage();
    CandidatosPage candidatosPage = new CandidatosPage();
    RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();
    DetalhePage detalhePage = new DetalhePage();
    @Test
    public void editarCandidatoValidoSteps() {

        cadastrarELogar();

        JSONObject candidatoCriado = adicionarCandidatoValido();
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(candidatoCriado.get("nome").toString());

        /**
         * Realizando a edição
         */

        JSONObject candidatoEditado = editarDadosPessoaisValido(posicaoCandidatoNaPagina);
        String nomeCandidatoEditado = candidatoEditado.get("nome").toString();

        /****
         *  Para validar que um candidato f*(*(*(*(***(
         */
        BaseTest.waitSeconds(5);

        // Recuperando o cep do candidato editado
        Integer posicaoCandidatoEditadoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoEditado);
        candidatosPage.abrirTelaDeDetalhes(posicaoCandidatoEditadoNaPagina);


        String contatoCandidatoEditado = detalhePage.recuperarContato().replace("(", "").replace(")", "");

        String telefone = candidatoCriado.get("telefone").toString();

        Assert.assertTrue(contatoCandidatoEditado.contains(telefone));
        Assert.assertNotEquals(candidatoCriado.get("nome"), nomeCandidatoEditado);


    }

    private JSONObject editarDadosPessoaisValido(Integer posicao) {
        candidatosPage.clicarBtnEditar(posicao);

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPaginaParaEdicao(candidatoCriado);
        registroDadosPessoaisCandidatoPage.popularCamposEscritosEdicaoNome(dadosPessoais);

        registroDadosPessoaisCandidatoPage.clicarBotaoProximoPosEdicao();
        registroEnderecoCandidatoPage.clicarBotaoProximoPosEdicao();
        registroEscolaridadeCandidatoPage.clicarBotaoProximoPosEdicao();
        registroExperienciasCandidatoPage.clicarBotaoProximoPosEdicao();

        return candidatoCriado;
    }

    private void cadastrarELogar() {
        cadastrar();
        logar();
    }

}


