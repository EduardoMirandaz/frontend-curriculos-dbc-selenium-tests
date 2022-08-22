package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.*;
import pages.CandidatosPages.CandidatoPopUp;
import pages.CandidatosPages.CandidatosPage;
import pages.CandidatosPages.RegistroCandidatoPage1;
import pages.UsuarioPages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.BaseTest;
import util.Browser;
import util.JsonManipulation;

import java.util.ArrayList;

import static util.Paths.curriculoValidoPath;

public class AdicionarCandidatoValidoSteps extends Browser {

    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    CandidatoPopUp candidatoPopUp = new CandidatoPopUp();
    CandidatosPage candidatosPage = new CandidatosPage();
    RegistroCandidatoPage1 registroCandidatoPage1 = new RegistroCandidatoPage1();
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


//        String mensagemBotaoVinculado =
//                BaseTest.retornarMensagemBotaoDoCandidatoDeAcordoComOIndex(indexPessoaVinculada);


//        Assert.assertEquals(mensagemBotaoVinculado, "Desvincular");


    }

    private String adicionarCandidatoValido() {
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.clicarBtnAdicionar();

        JSONObject candidatoCriado = JsonManipulation.criarJsonCandidato();
        ArrayList<String> keys = registroCandidatoPage1.recuperarAtributosPrimeiraPagina(candidatoCriado);
        registroCandidatoPage1.popularCamposEscritos(keys);
        registroCandidatoPage1.adicionarArquivo(curriculoValidoPath);


        return "";
    }


    private Integer vincularCandidatoValido() {

        dashboardPage.clicarBtnVincularCandidato();
        return candidatoPopUp.clicarBtnVincular();


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


