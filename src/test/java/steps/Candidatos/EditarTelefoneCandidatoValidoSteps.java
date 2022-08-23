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
        String nomeCandidatoCriado = candidatoCriado.get("nome").toString();
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoCriado);

        /**
         * Realizando a edição
         */

        editarDadosPessoaisValido(posicaoCandidatoNaPagina);

        /****
         *  Para validar que um candidato deve de fato seu telefone editado,
         *  busco pelo nome e verifico se o telefone foi alterado;
         */
        BaseTest.waitSeconds(5);

        Integer posicaoCandidatoEditadoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoCriado);
        candidatosPage.abrirTelaDeDetalhes(posicaoCandidatoEditadoNaPagina);
        BaseTest.waitSeconds(2);

        /**
         * Verifico que o número foi de fato alterado e depois verifico se o nome permaneceu o mesmo.
         */
        String contatoCandidatoEditado = detalhePage.recuperarContato().replace("(", "").replace(")", "");
        String contatoCandidatoCriado = candidatoCriado.get("telefone").toString();

        Assert.assertNotEquals(contatoCandidatoCriado, contatoCandidatoEditado);
        Assert.assertTrue(nomeCandidatoCriado.equalsIgnoreCase(detalhePage.recuperarNome()));

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


