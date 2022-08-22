    package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.Elements.element;


    public class RegistroEnderecoCandidatoPage {

    public final By cep = By.cssSelector("#cep");

    public void popularCamposEscritos(ArrayList<String> keys) {
        BaseTest.sendKeysPage1(cep, keys);
    }

    public void clicarBotaoProximo() {
        BaseTest.clicarBotaoProximo(cep);
    }

    public void clicarBotaoProximoPosEdicao() {
        element(cep).sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB,Keys.TAB,Keys.TAB, Keys.ENTER);
    }



    public ArrayList<String> recuperarAtributosEndereco(JSONObject candidatoCriado) {
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject endereco = (JSONObject) candidatoCriado.get("endereco");

        return Stream.of(endereco.get("cep"),
                        endereco.get("logradouro"),
                        endereco.get("numero"),
                        endereco.get("bairro"),
                        endereco.get("cidade"))
                .map(e -> objectMapper.convertValue(e, String.class))
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
