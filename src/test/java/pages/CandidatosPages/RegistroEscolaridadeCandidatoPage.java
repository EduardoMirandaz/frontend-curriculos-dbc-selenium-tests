    package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.Elements.element;


    public class RegistroEscolaridadeCandidatoPage {

    public final By nivel = By.cssSelector("#nivel");
    public final By curso = By.cssSelector("#descricao");

    public void popularCamposEscritos(ArrayList<String> keys) {
        BaseTest.sendKeysPage3(curso, keys);
    }

    public void preencherNivel() {
        BaseTest.preencherNivel(nivel);
    }

    public void clicarBotaoProximo() {
        BaseTest.clicarBotaoProximo(curso);
    }

    public void clicarBotaoProximoPosEdicao() {
        element(curso).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,Keys.TAB,Keys.TAB, Keys.ENTER);
    }

    public ArrayList<String> recuperarAtributosEscolaridade(JSONObject candidatoCriado) {
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<JSONObject> escolaridades = (ArrayList<JSONObject>) candidatoCriado.get("escolaridades");
        JSONObject escolaridade = escolaridades.get(0);


        return Stream.of(
                        escolaridade.get("descricao"),
                        escolaridade.get("instituicao"),
                        recuperarData(escolaridade.get("dataInicio")),
                        recuperarData(escolaridade.get("dataFim")))
                .map(e -> objectMapper.convertValue(e, String.class))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private Object recuperarData(Object dataNascimento) {
        if(dataNascimento != ""){
            String[] dataList = dataNascimento.toString().split("-");
            return dataList[2] + dataList[1] +dataList[0];
        }
        return "";
    }

}
