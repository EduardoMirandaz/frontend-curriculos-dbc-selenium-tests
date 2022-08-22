    package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        String[] dataList = dataNascimento.toString().split("-");
        return dataList[2] + dataList[1] +dataList[0];
    }

}
