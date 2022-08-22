package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.*;
import pages.DashboardPage;
import pages.UsuarioPages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

import java.util.ArrayList;

import static steps.Candidatos.AdicionarCandidatoValidoSteps.adicionarCandidatoValido;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.logar;
import static util.Paths.curriculoValidoPath;

public class EditarCandidatoValidoSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();
    CandidatosPage candidatosPage = new CandidatosPage();
    RegistroDadosPessoaisCandidatoPage registroDadosPessoaisCandidatoPage = new RegistroDadosPessoaisCandidatoPage();
    RegistroEnderecoCandidatoPage registroEnderecoCandidatoPage = new RegistroEnderecoCandidatoPage();
    RegistroEscolaridadeCandidatoPage registroEscolaridadeCandidatoPage = new RegistroEscolaridadeCandidatoPage();
    RegistroExperienciasCandidatoPage registroExperienciasCandidatoPage = new RegistroExperienciasCandidatoPage();
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


    private void cadastrarELogar() {
        cadastroValidoSteps.cadastrar();
        logar();
    }



}


