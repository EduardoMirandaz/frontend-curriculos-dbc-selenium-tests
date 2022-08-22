package steps.Candidatos;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pages.CandidatosPages.CandidatosPage;
import pages.Toasts;
import util.BaseTest;
import util.Browser;

import java.util.NoSuchElementException;

import static steps.Cadastro.CadastroValidoSteps.cadastrar;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.adicionarCandidatoValido;
import static steps.Candidatos.AdicionarCandidatoValidoSteps.dashboardPage;
import static steps.Login.LogInValidoSteps.logar;
import static util.BaseTest.waitSeconds;


public class DeletarCandidatoValidoSteps extends Browser {

    static CandidatosPage candidatosPage = new CandidatosPage();
    Toasts toasts = new Toasts();
    @Test
    public void deletarCandidatoValidoSteps() {

        cadastrarELogar();

        JSONObject candidatoCriado = adicionarCandidatoValido();
        Integer posicaoCandidatoNaPagina = candidatosPage.buscarCandidatoPorNome(candidatoCriado.get("nome").toString());

        /**
         * Realizando a deleção
         */
        deletarCandidatoValido(posicaoCandidatoNaPagina);

        /****
         *  Para validar que um candidato f*(*(*(*(***(
         */

        dashboardPage.clicarBtnDashboard();
        waitSeconds(2);
        dashboardPage.clicarBtnCandidatos();
        candidatosPage.buscarCandidatoPorNome(candidatoCriado.get("nome").toString());
    }

    public static void deletarCandidatoValido(Integer posicao) {
        candidatosPage.clicarBtnDeletar(posicao);
    }

    private void cadastrarELogar() {
        cadastrar();
        logar();
    }

}


