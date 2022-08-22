package pages.CandidatosPages;

import org.openqa.selenium.By;
import util.BaseTest;

public class CandidatosPage {

    public static final By btnAdicionar = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > button");

    public void clicarBtnAdicionar(){
        BaseTest.click(btnAdicionar);
    }
}
