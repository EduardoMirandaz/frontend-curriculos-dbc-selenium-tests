package steps;

import pages.LoginPage;
import steps.Cadastro.CadastroValidoSteps;
import util.Browser;

public class LogInSteps extends Browser {


    LoginPage loginPage = new LoginPage();
    CadastroValidoSteps cadastroValidoSteps = new CadastroValidoSteps();
//    @Test
//    public void logarUsuarioValido(){
//        cadastroValidoSteps.cadastrar();
//        // Clicar no botao de logout e voltar para a SignIn_LoginPage
//        myAccountPage.clicarLogout();
//        logar();
//
//        // Validação
//
//        String expected = myAccountPage.myAccountMessage();
//        Assert.assertEquals(expected, "MY ACCOUNT");
//    }
//
//    void logar() {
//
//        homePage.clickSignIn();
//        // preencher dados do login
//        loginPage.preencherEmail();
//        loginPage.preencherPassword();
//        signIn_loginPage.clicarBtnLogin();
//    }
//
//    public void cadastrarDeslogarELogar(){
//        cadastroValidoSteps.cadastrar();
//        // Clicar no botao de logout e voltar para a SignIn_LoginPage
//        myAccountPage.clicarLogout();
//        logar();
//    }


}
