package steps.Cadastro;

import org.junit.Assert;
import org.junit.Test;
import pages.UsuarioPages.LoginPage;
import pages.UsuarioPages.RegisterPage;
import util.Browser;
import util.JsonManipulation;

import java.util.HashMap;
import java.util.Map;

import static util.Elements.waitElement;

public class CadastroConfirmacaoInvalidaDeSenha extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void cadastroConfirmacaoInvalidaDeSenha(){

        cadastrarConfirmacaoInvalidaDeSenha();

        // Validação

        String expected = registerPage.recuperarMensagemConfirmarSenhaValida();
        Assert.assertEquals(expected, "AS SENHAS DIGITADAS SÃO DIFERENTES");
    }

    public void cadastrarConfirmacaoInvalidaDeSenha(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail();
        String password = registerPage.preencherSenha();
        registerPage.preencherConfirmarSenha(registerPage.preencherSenha());

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("senha", password);
        JsonManipulation.criarJsonCadastro(login);

    }


}
