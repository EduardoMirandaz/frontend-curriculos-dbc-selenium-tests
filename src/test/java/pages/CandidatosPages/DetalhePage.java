package pages.CandidatosPages;

import org.openqa.selenium.By;
import util.BaseTest;
import util.Enums.AtributosTelaDeDetalhe;

public class DetalhePage {

    public final By contato = By.cssSelector("#root > div:nth-child(1) > div:nth-child(2) > div > div.sc-gKXOVf.gEdGKo > div > div.sc-ftvSup.hOhBvI > div:nth-child(4)");
    public final By nome = By.cssSelector("#root > div:nth-child(1) > div:nth-child(2) > div > div.sc-gKXOVf.gEdGKo > div > div.sc-ftvSup.hOhBvI > h2");


    public final String inicioMapeamentoEscolaridade = "#root > div:nth-child(1) > div:nth-child(2) > div > div.sc-gKXOVf.gEdGKo > div > div.sc-jqUVSM.cTAnGq > div:nth-child(1) > ul > li:nth-child(2) > p:nth-child(";
    public final String finalMapeamentoEscolaridade = ")";


    public final String inicioMapeamentoExperiencias = "#root > div:nth-child(1) > div:nth-child(2) > div > div.sc-gKXOVf.gEdGKo > div > div.sc-jqUVSM.cTAnGq > div:nth-child(2) > ul > li:nth-child(2) > p:nth-child(";
    public final String finalMapeamentoExperiencias = ")";



    public String recuperarContato(){
        return BaseTest.getText(contato);
    }

    public String recuperarNome(){
        return BaseTest.getText(nome);
    }

    public String recuperarAtributoEscolaridade(AtributosTelaDeDetalhe campo){
        int indice = campo.ordinal() + 1;
        return BaseTest.getText(By.cssSelector(inicioMapeamentoEscolaridade + indice + finalMapeamentoEscolaridade));
    }

    public String recuperarAtributoExperiencia(AtributosTelaDeDetalhe campo){
        int indice = campo.ordinal() - 3;
        return BaseTest.getText(By.cssSelector(inicioMapeamentoExperiencias + indice + finalMapeamentoExperiencias));
    }

}
