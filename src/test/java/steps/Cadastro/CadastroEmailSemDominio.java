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

public class CadastroEmailSemDominio extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    Geradores geradores = new Geradores();
    @Test
    public void cadastroEmailSemDominio(){

        /**
         * Tentando cadastrar um email sem domínio.
         */
        cadastrarEmailSemDominio();

        // Validação

        String expected = registerPage.recuperarMensagemEmailInvalido();
        Assert.assertEquals(expected, "EMAIL INVÁLIDO");
    }

    public void cadastrarEmailSemDominio(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail(geradores.gerarLoginRandomico(TipoDeInvalidacao.EMAILSEMDOMINIO));
        String password = registerPage.preencherSenha();
        registerPage.preencherConfirmarSenha(password);

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("senha", password);
        JsonManipulation.criarJsonCadastro(login);



    }


}
