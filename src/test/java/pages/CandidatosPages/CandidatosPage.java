package pages.CandidatosPages;

import org.openqa.selenium.By;
import util.BaseTest;

public class CandidatosPage {

    public static final By btnAdicionar = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > button");

    public static final String inicioMapeamentoDelecaoCandidato = "#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(";
    public static final String finalMapeamentoDelecaoCandidato = ") > div > button:nth-child(2)";

    // #root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(2) > p:nth-child(1)
    // #root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(2) > div > button:nth-child(2)
    // #root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(2) > p:nth-child(1)
    public static final String inicioMapeamentoEdicaoCandidato = "#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(";
    public static final String finalMapeamentoEdicaoCandidato = ") > div > button:nth-child(1)";


    public static final String inicioMapeamentoCandidato = "#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > div.sc-gKXOVf.gEdGKo > ul > li:nth-child(";
    public static final String finalMapeamentoCandidato = ") > p:nth-child(1)";

    public static final By btnProximaPagina = By.id("proximaPagina");



    public void clicarBtnAdicionar(){
        BaseTest.click(btnAdicionar);
    }

    public int buscarCandidatoPorNome(String nomeCandidatoCriado) {
        return BaseTest.buscarCandidatoPorNome(nomeCandidatoCriado);
    }

    public void clicarBtnEditar(Integer posicao) {
        BaseTest.click(By.cssSelector(inicioMapeamentoEdicaoCandidato + posicao + finalMapeamentoEdicaoCandidato));
    }

    public void clicarBtnDeletar(Integer posicao) {
        BaseTest.click(By.cssSelector(inicioMapeamentoDelecaoCandidato + posicao + finalMapeamentoDelecaoCandidato));
    }

    public void abrirTelaDeDetalhes(Integer posicaoCandidatoEditadoNaPagina) {
        BaseTest.click(By.cssSelector(inicioMapeamentoCandidato + posicaoCandidatoEditadoNaPagina + finalMapeamentoCandidato));
    }


}
