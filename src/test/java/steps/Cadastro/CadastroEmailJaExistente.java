package steps.Cadastro;

import org.junit.Assert;
import org.junit.Test;
import pages.UsuarioPages.LoginPage;
import pages.UsuarioPages.RegisterPage;
import util.Browser;
import util.JsonManipulation;

import java.util.HashMap;
import java.util.Map;

import static util.BaseTest.waitSeconds;
import static util.Elements.waitElement;

public class CadastroEmailJaExistente extends Browser {

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage = new LoginPage();

    @Test
    public void cadastroEmailJaExistente(){

        /**
         * Tentando cadastrar um usuário com email que eu já tinha cadastrado.
         */
        String emailCadastrado = cadastrarEmailValido();
        waitSeconds(3);
        cadastrarMesmoEmail(emailCadastrado);


        // Validação
        // Valido que permaneci na mesma página, isto é, não consegui cadastrar.
        String expected = registerPage.retornoRegistrar();
        Assert.assertEquals(expected, "Registre uma conta");
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
