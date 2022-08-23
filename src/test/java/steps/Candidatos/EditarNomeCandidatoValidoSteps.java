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
import static steps.Candidatos.DeletarCandidatoValidoSteps.deletarCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;
import static util.BaseTest.waitSeconds;


public class EditarNomeCandidatoValidoSteps extends Browser {

    DashboardPage dashboardPage = new DashboardPage();
    CandidatosPage candidatosPage = new CandidatosPage();
    RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();
    DetalhePage detalhePage = new DetalhePage();
    @Test
    public void editarNomeCandidatoValidoSteps() {

        cadastrarELogar();

        JSONObject candidatoCriado = adicionarCandidatoValido();
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(candidatoCriado.get("nome").toString());

        /**
         * Realizando a edição
         */
        JSONObject candidatoEditado = editarDadosPessoaisValido(posicaoCandidatoNaPagina);
        String nomeCandidatoEditado = candidatoEditado.get("nome").toString();

        /****
         *  Para validar que um candidato deve de fato seu nome editado,
         *  busco o novo nome e verifico se os outros dados batem com o anterior;
         */
        waitSeconds(5);

        Integer posicaoCandidatoEditadoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoEditado);

        candidatosPage.abrirTelaDeDetalhes(posicaoCandidatoEditadoNaPagina);

        waitSeconds(2);

        String contatoCandidatoEditado = detalhePage.recuperarContato().replace("(", "").replace(")", "");

        String telefone = candidatoCriado.get("telefone").toString();

        Assert.assertTrue(contatoCandidatoEditado.contains(telefone));
        Assert.assertNotEquals(candidatoCriado.get("nome"), nomeCandidatoEditado);

        /**
         * Deletando candidato após criação válida.
         */
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.buscarCandidatoPorNome(nomeCandidatoEditado);
        deletarCandidatoValido(posicaoCandidatoNaPagina);
    }

    private JSONObject editarDadosPessoaisValido(Integer posicao) {
        candidatosPage.clicarBtnEditar(posicao);

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPaginaParaEdicao(candidatoCriado, "nome");
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


