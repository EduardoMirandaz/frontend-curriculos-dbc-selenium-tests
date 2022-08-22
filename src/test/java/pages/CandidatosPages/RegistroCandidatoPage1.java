package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class RegistroCandidatoPage1 {

    public final By nome = By.cssSelector("#nome");
    public final By arquivo = By.cssSelector("#personalFile");

    public void popularCamposEscritos(ArrayList<String> keys) {
        BaseTest.sendKeysPage1(nome, keys);
    }

    public void adicionarArquivo(String path){
        BaseTest.sendKeys(arquivo, path);
    }


    public ArrayList<String> recuperarAtributosPrimeiraPagina(JSONObject candidatoCriado) {
        ObjectMapper objectMapper = new ObjectMapper();
        return Stream.of(candidatoCriado.get("nome"),
                        candidatoCriado.get("cpf"),
                        recuperarData(candidatoCriado.get("dataNascimento")),
                        candidatoCriado.get("telefone"),
                        candidatoCriado.get("cargo"))
                .map(e -> objectMapper.convertValue(e, String.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Object recuperarData(Object dataNascimento) {
        String[] dataList = dataNascimento.toString().split("-");
        return dataList[2] + dataList[1] +dataList[0];
    }



}
