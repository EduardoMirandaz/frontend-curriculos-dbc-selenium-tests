package pages.CandidatosPages;

import org.openqa.selenium.By;
import util.BaseTest;

import static pages.CandidatosPages.CandidatoPopUp.strBtnVincularDesvincularFinal;
import static pages.CandidatosPages.CandidatoPopUp.strBtnVincularDesvincularInicio;

public class CandidatosPage {

    public static final By btnAdicionar = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > button");

    public static final String inicioMapeamentoCandidato = "#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(";

    public static final String finalMapeamentoCandidato = ") > p:nth-child(1)";

    public static final By btnProximaPagina = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > div > button:nth-child(14)");

    public void clicarBtnAdicionar(){
        BaseTest.click(btnAdicionar);
    }


    public boolean buscarCandidatoPorNome(String nomeCandidatoCriado) {
        return BaseTest.buscarCandidatoPorNome(nomeCandidatoCriado) != 0;
    }
}
