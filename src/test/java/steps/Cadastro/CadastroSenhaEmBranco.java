package steps.Cadastro;

import org.junit.Assert;
import org.junit.Test;
import pages.UsuarioPages.LoginPage;
import pages.UsuarioPages.RegisterPage;
import util.Browser;
import util.Enums.TipoDeInvalidacao;
import util.Geradores;
import util.JsonManipulation;

import java.util.HashMap;
import java.util.Map;

import static util.Elements.waitElement;

public class CadastroSenhaEmBranco extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    Geradores geradores = new Geradores();
    @Test
    public void cadastroSenhaEmBranco(){

        cadastrarSenhaEmBranco();

        // Validação

        String expected = registerPage.recuperarMensagemSenhaInvalida();
        Assert.assertEquals(expected, "- OBRIGATÓRIO");
    }

    public void cadastrarSenhaEmBranco(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail();
        String password = registerPage.preencherSenha(geradores.gerarLoginRandomico(TipoDeInvalidacao.EMBRANCO));
        registerPage.preencherConfirmarSenha(password);

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("senha", password);
        JsonManipulation.criarJsonCadastro(login);

    }


}
