package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.*;
import pages.DashboardPage;
import util.BaseTest;
import util.Browser;
import util.Enums.AtributosTelaDeDetalhe;
import util.JsonManipulation;

import java.util.ArrayList;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Candidatos.DeletarCandidatoValidoSteps.deletarCandidatoValido;
import static steps.Login.LogInValidoSteps.logar;
import static util.BaseTest.waitSeconds;
import static util.Paths.curriculoValidoPath;

public class AdicionarCandidatoValidoSteps extends Browser {

    static DashboardPage dashboardPage = new DashboardPage();
    static CandidatosPage candidatosPage = new CandidatosPage();
    static DetalhePage detalhePage = new DetalhePage();
    static RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    static RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    static RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    static RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();

    @Test
    public void adicionarCandidatoValidoSteps() {

        cadastrarELogar();

        JSONObject candidatoCriado = adicionarCandidatoValido();
        String nomeCandidatoCriado = candidatoCriado.get("nome").toString();

        /****
         *  Para validar que um candidato foi de fato vinculado, busco ele na base
         *  de candidatos e verifico os datalhes do mesmo.
         */
        waitSeconds(5);
        // Recuperando posicao candidato na pagina.
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(nomeCandidatoCriado);

        // Verificando TODOS os detalhes do candidato.
        Assert.assertTrue(verificarDetalhesCandidato(candidatoCriado, posicaoCandidatoNaPagina));

        Assert.assertTrue( posicaoCandidatoNaPagina != 0);

        /**
         * Deletando candidato após criação válida.
         */
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.buscarCandidatoPorNome(nomeCandidatoCriado);
        waitSeconds(4);
        deletarCandidatoValido(posicaoCandidatoNaPagina);

    }

    public static JSONObject adicionarCandidatoValido() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> dadosPessoais = registroDadosPessoaisCandidatoPage.recuperarAtributosPrimeiraPagina(candidatoCriado);
        registroDadosPessoaisCandidatoPage.popularCamposEscritos(dadosPessoais);
        registroDadosPessoaisCandidatoPage.adicionarArquivo(curriculoValidoPath);
        registroDadosPessoaisCandidatoPage.clicarBotaoProximo();
        waitSeconds(4);

        ArrayList<String> endereco = registroEnderecoCandidatoPage.recuperarAtributosEndereco(candidatoCriado);
        registroEnderecoCandidatoPage.popularCamposEscritos(endereco);
        registroEnderecoCandidatoPage.clicarBotaoProximo();
        waitSeconds(4);

        ArrayList<String> escolaridades = registroEscolaridadeCandidatoPage.recuperarAtributosEscolaridade(candidatoCriado);
        registroEscolaridadeCandidatoPage.preencherNivel();
        registroEscolaridadeCandidatoPage.popularCamposEscritos(escolaridades);
        registroEscolaridadeCandidatoPage.clicarBotaoProximo();
        waitSeconds(4);

        ArrayList<String> experiencias = registroExperienciasCandidatoPage.recuperarAtributosExperiencias(candidatoCriado);
        registroExperienciasCandidatoPage.popularCamposEscritos(experiencias);
        registroExperienciasCandidatoPage.clicarBotaoProximo();
        waitSeconds(4);


        return candidatoCriado;
    }

    public static boolean verificarDetalhesCandidato(JSONObject candidato, Integer posicaoCandidatoNaTela){
        candidatosPage.abrirTelaDeDetalhes(posicaoCandidatoNaTela);
        waitSeconds(5);

        String nomeNaPagina = detalhePage.recuperarNome().replace("...", "").toLowerCase();
        String nomeEnviado = candidato.get("nome").toString().toLowerCase();
        boolean nomeValido = nomeEnviado.contains(nomeNaPagina);


        String telefoneNaPagina = detalhePage.recuperarContato().replace("Contato: (", "").replace(")", "");
        String telefoneEnviado = candidato.get("telefone").toString();
        boolean telefoneValido = telefoneEnviado.contains(telefoneNaPagina);


        boolean escolaridadeValida = verificarEscolaridadeValida(candidato);


        boolean experienciaValida = verificarExperienciaValida(candidato);


        return nomeValido && telefoneValido && escolaridadeValida && experienciaValida;
    }

    private static boolean verificarExperienciaValida(JSONObject candidato) {
        String instituicaoExperienciaNaPagina = detalhePage.recuperarAtributoExperiencia(AtributosTelaDeDetalhe.EXPERIENCIA_INSITUICAO).replace("...", "");
        String instituicaoExperienciaEnviada = recuperarAtributoEnviado(candidato, "experiencias", "instituicao").toString();
        boolean instituicaoExperienciaValida = instituicaoExperienciaEnviada.contains(instituicaoExperienciaNaPagina);

        String cargoNaPagina = detalhePage.recuperarAtributoExperiencia(AtributosTelaDeDetalhe.EXPERIENCIA_CARGO).replace("...", "");
        String cargoEnviado = recuperarAtributoEnviado(candidato, "experiencias", "cargo").toString();
        boolean cargoValido = cargoEnviado.contains(cargoNaPagina);

        String dataInicioExperiencia = detalhePage.recuperarAtributoExperiencia(AtributosTelaDeDetalhe.EXPERIENCIA_DATA_INICIO);
        String dataInicioEnviada = recuperarData(recuperarAtributoEnviado(candidato, "experiencias", "dataInicio")).toString();
        boolean dataInicioValida = dataInicioExperiencia.equals(dataInicioEnviada);

        String dataFimExperiencia = detalhePage.recuperarAtributoExperiencia(AtributosTelaDeDetalhe.EXPERIENCIA_DATA_FIM);
        String dataFimEnviada = recuperarData(recuperarAtributoEnviado(candidato, "experiencias", "dataFim")).toString();
        boolean dataFimValida = dataFimExperiencia.equals(dataFimEnviada);

        return instituicaoExperienciaValida && cargoValido && dataInicioValida && dataFimValida;
    }

    private static boolean verificarEscolaridadeValida(JSONObject candidato){

        String instituicaoEscolaridadeNaPagina = detalhePage.recuperarAtributoEscolaridade(AtributosTelaDeDetalhe.ESCOLARIDADE_INSITUICAO).replace("...", "");
        String instituicaoEscolaridadeEnviada = recuperarAtributoEnviado(candidato, "escolaridades", "instituicao").toString();
        boolean instituicaoEscolaridadeValida = instituicaoEscolaridadeEnviada.contains(instituicaoEscolaridadeNaPagina);

        String cursoNaPagina = detalhePage.recuperarAtributoEscolaridade(AtributosTelaDeDetalhe.ESCOLARIDADE_CURSO).replace("...", "");
        String cursoEnviado = recuperarAtributoEnviado(candidato, "escolaridades", "descricao").toString();
        boolean cursoValido = cursoEnviado.contains(cursoNaPagina);

        String dataInicioEscolaridade = detalhePage.recuperarAtributoEscolaridade(AtributosTelaDeDetalhe.ESCOLARIDADE_DATA_INICIO);
        String dataInicioEnviada = recuperarData(recuperarAtributoEnviado(candidato, "escolaridades", "dataInicio")).toString();
        boolean dataInicioValida = dataInicioEscolaridade.equals(dataInicioEnviada);

        String dataFimEscolaridade = detalhePage.recuperarAtributoEscolaridade(AtributosTelaDeDetalhe.ESCOLARIDADE_DATA_FIM);
        String dataFimEnviada = recuperarData(recuperarAtributoEnviado(candidato, "escolaridades", "dataFim")).toString();
        boolean dataFimValida = dataFimEscolaridade.equals(dataFimEnviada);

        return instituicaoEscolaridadeValida && cursoValido && dataInicioValida && dataFimValida;
    }

    private static Object recuperarAtributoEnviado(JSONObject candidato, String subObject, String campo) {
        return ((ArrayList<JSONObject>) candidato.get(subObject)).get(0).get(campo);
    }


    private static Object recuperarData(Object dataNascimento) {
        String[] dataList = dataNascimento.toString().split("-");
        return dataList[2] + "/" + dataList[1] + "/" +dataList[0];
    }

    private void cadastrarELogar() {
        cadastrar();
        waitSeconds(4);
        logar();
        waitSeconds(4);
    }



}


