package steps.Cadastro;

import org.junit.Test;
import pages.UsuarioPages.LoginPage;
import pages.UsuarioPages.RegisterPage;
import util.Browser;
import util.JsonManipulation;

import java.util.HashMap;
import java.util.Map;

import static util.Elements.waitElement;

public class CadastroEmailJaExistente extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void cadastroEmailJaExistente(){

        String emailCadastrado = cadastrarEmailValido();
        cadastrarMesmoEmail(emailCadastrado);

        // Validação

//        String expected = registerPage.recuperarMensagemEmailInvalido();
//        Assert.assertEquals(expected, "OBRIGATÓRIO");
    }

    public String cadastrarEmailValido(){
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();

        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        String email = registerPage.preencherEmail();
        String password = registerPage.preencherSenha();
        registerPage.preencherConfirmarSenha(password);

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("senha", password);
        JsonManipulation.criarJsonCadastro(login);

        //         Clicar no botao submit
        registerPage.clicarBtnRegistrarSe();

        return email;

    }

    private void cadastrarMesmoEmail(String email) {
        // Clicar no botao para registrar
        loginPage.clicarBtnRegistrar();


        // Esperar elemento da pagina de registro
        waitElement(registerPage.msgRegistreUmaConta);

        // preencher formulário de cadastro
        registerPage.preencherEmail(email);
        String password = registerPage.preencherSenha();
        registerPage.preencherConfirmarSenha(password);
//         Clicar no botao submit
        registerPage.clicarBtnRegistrarSe();
    }


}
