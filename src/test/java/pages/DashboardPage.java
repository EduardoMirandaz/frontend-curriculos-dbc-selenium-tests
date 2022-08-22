package pages;

import org.openqa.selenium.By;
import util.BaseTest;

public class DashboardPage {

    public final By btnDeslogar = By.cssSelector("#root > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)");
    public final By msgListaDeVagas = By.cssSelector("#root > div:nth-child(1) > div:nth-child(2) > div > h1");
    public final By btnCandidatos = By.cssSelector("#root > div:nth-child(1) > div.sc-dkzDqf.MVQyv > header > div > nav > ul > a:nth-child(2)");

    public final By btnDashboard = By.cssSelector("#root > div:nth-child(1) > div.sc-dkzDqf.MVQyv > header > div > nav > ul > a:nth-child(1)");

    public final By btnVincularCandidato = By.cssSelector("#root > div:nth-child(1) > div:nth-child(2) > div > div.sc-gicCDI.iIrtlB > div:nth-child(1) > div > button");



    public String recuperarMensagemListaDeVagas(){
        return BaseTest.getText(msgListaDeVagas);
    }
    public void clicarBtnCandidatos(){
        BaseTest.click(btnCandidatos);
    }

    public void clicarBtnDashboard(){
        BaseTest.click(btnDashboard);
    }

    public void clicarBtnVincularCandidato(){
        BaseTest.click(btnVincularCandidato);
    }

}
