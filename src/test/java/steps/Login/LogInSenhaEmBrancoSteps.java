package steps.Login;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;
import util.Enums.TipoDeInvalidacao;
import util.Geradores;
import util.JsonManipulation;

public class LogInSenhaEmBrancoSteps extends Browser {


    static LoginPage loginPage = new LoginPage();
    static Geradores geradores = new Geradores();

    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
    @Test
    public void logInSenhaEmBrancoSteps(){
        cadastroValidoSteps.cadastrar();

        logar();

        // Validação

        Assert.assertEquals(loginPage.recuperarMsgErroSenha(), "OBRIGATÓRIO");


    }

    public static void logar() {
        loginPage.preencherEmail(JsonManipulation.recuperarCadastro().get("email"));
        loginPage.preencherPassword(geradores.gerarSenhaRandomica(TipoDeInvalidacao.EMBRANCO));

        loginPage.clicarBtnLogin();
    }


}
