package pages.CandidatosPages;

import org.openqa.selenium.By;
import util.BaseTest;

public class CandidatoPopUp {

    public static final By btnCandidatos = By.cssSelector("#root > div:nth-child(1) > div.sc-dkzDqf.MVQyv > header > div > nav > ul > a:nth-child(2)");
    public static final String strBtnVincularDesvincularInicio = "body > div:nth-child(4) > div > div > div:nth-child(";
    public static final String strBtnVincularDesvincularFinal = ") > div";
    public static final Integer pos = 2;


    public static final By btnVincularDesvincular = By.cssSelector("body > div:nth-child(4) > div > div > div:nth-child(2) > div");


    public void clicarBtnDesvincular() {
        BaseTest.click(BaseTest.buscarBotaoDesvincular(strBtnVincularDesvincularInicio, strBtnVincularDesvincularFinal, 2));
    }
    public void clicarBtnCandidatos() {
        BaseTest.click(btnCandidatos);
    }

    public Integer clicarBtnVincular() {
        Integer indexPessoaQuePodeSerVinculada = BaseTest.encontrarIndexPessoaPossivelDeSerVinculada(2);

        BaseTest.clicarBotaoVincular(indexPessoaQuePodeSerVinculada);

        return indexPessoaQuePodeSerVinculada;

    }
}
