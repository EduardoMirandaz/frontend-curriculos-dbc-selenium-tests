package steps.Cadastro;

import org.junit.Assert;
import org.junit.Test;
import pages.DashboardPage;
import pages.UsuarioPages.LoginPage;
import pages.UsuarioPages.RegisterPage;
import util.Browser;
import util.Geradores;
import util.JsonManipulation;

import java.util.HashMap;
import java.util.Map;

import static steps.Login.LogInValidoSteps.logar;
import static util.Elements.waitElement;

public class CadastroValidoSteps extends Browser {

    static RegisterPage registerPage = new RegisterPage();
    static LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    static Geradores geradores = new Geradores();
    @Test
    public void cadastrarUsuarioValidoELogarValido(){

        /**
         * Realizando um cadastro válido
         */
        cadastrar();

        // Validação
        /*********************************************************
         Para validar se o registro foi efetuado de maneira idonea
         faço login e verifico se o texto "Lista de vagas" foi encon
         trado na pagina de dashboard de vagas.
         */
        logar();

        Assert.assertEquals(dashboardPage.recuperarMensagemListaDeVagas(), "Lista de vagas");

    }


    public static void cadastrar(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail(geradores.gerarLoginRandomico());
        String password = registerPage.preencherSenha(geradores.gerarSenhaRandomica());
        registerPage.preencherConfirmarSenha(password);

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("senha", password);
        JsonManipulation.criarJsonCadastro(login);

//         Clicar no botao submit
        registerPage.clicarBtnRegistrarSe();

    }


}
