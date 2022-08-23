package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.*;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

import java.util.ArrayList;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.adicionarCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;


public class EditarTelefoneCandidatoValidoSteps extends Browser {

    DashboardPage dashboardPage = new DashboardPage();
    CandidatosPage candidatosPage = new CandidatosPage();
    RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();
    DetalhePage detalhePage = new DetalhePage();
    @Test
    public void editarTelefoneCandidatoValidoSteps() {

        cadastrarELogar();

        JSONObject candidatoCriado = adicionarCandidatoValido();
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(candidatoCriado.get("nome").toString());

        /**
         * Realizando a edição
         */

        JSONObject candidatoEditado = editarDadosPessoaisValido(posicaoCandidatoNaPagina);
        String nomeCandidatoEditado = candidatoEditado.get("nome").toString();

        /****
         *  Para validar que um candidato deve de fato seu telefone editado,
         *  busco pelo nome e verifico se o telefone foi alterado;
         */
        BaseTest.waitSeconds(5);

        // Recuperando o cep do candidato editado
        Integer posicaoCandidatoEditadoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoEditado);
        candidatosPage.abrirTelaDeDetalhes(posicaoCandidatoEditadoNaPagina);


        String contatoCandidatoEditado = detalhePage.recuperarContato().replace("(", "").replace(")", "");

        String telefone = candidatoCriado.get("telefone").toString();

        Assert.assertNotEquals(telefone, contatoCandidatoEditado);
        Assert.assertEquals(candidatoCriado.get("nome"), nomeCandidatoEditado);

    }

    private JSONObject editarDadosPessoaisValido(Integer posicao) {
        candidatosPage.clicarBtnEditar(posicao);

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPaginaParaEdicao(candidatoCriado, "telefone");
        registroDadosPessoaisCandidatoPage.popularCamposEscritosEdicaoTelefone(dadosPessoais);

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


