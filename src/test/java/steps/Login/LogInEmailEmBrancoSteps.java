package steps.Login;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.Enums.TipoDeInvalidacao;
import util.Geradores;
import util.JsonManipulation;

public class LogInEmailEmBrancoSteps extends Browser {


    static LoginPage loginPage = new LoginPage();
    static Geradores geradores = new Geradores();

    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void logInEmailEmBrancoSteps(){
        cadastroValidoSteps.cadastrar();

        logar();

        // Validação

        Assert.assertEquals(loginPage.recuperarMsgErroEmail(), "OBRIGATÓRIO");


    }

    public static void logar() {
        loginPage.preencherEmail(geradores.gerarLoginRandomico(TipoDeInvalidacao.EMBRANCO));
        loginPage.preencherPassword(JsonManipulation.recuperarCadastro().get("password"));

        loginPage.clicarBtnLogin();
    }


}
