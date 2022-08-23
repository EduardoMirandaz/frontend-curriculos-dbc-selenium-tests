package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class RegistroDadosPessoaisCandidatoPage {

    public final By nome = By.cssSelector("#nome");
    public final By arquivo = By.cssSelector("#personalFile");
    public final By msgErroCpf = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > form > div.sc-idiyUo.jMjUas > div.sc-fnykZs.gmrVMo > div:nth-child(2) > span");
    public final By msgErroDOB = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > form > div.sc-idiyUo.jMjUas > div.sc-fnykZs.gmrVMo > div:nth-child(3) > span");
    public final By msgErroTelefone = By.cssSelector("#root > div:nth-child(1) > div.sc-eCYdqJ.dcdMBJ > form > div.sc-idiyUo.jMjUas > div.sc-fnykZs.drHFBa > div:nth-child(1) > span");

    public void popularCamposEscritos(ArrayList<String> keys) {
        BaseTest.sendKeysPage1(nome, keys);
    }

    public void popularCamposEscritosTelefoneEmBranco(ArrayList<String> keys) {
        BaseTest.sendKeysPage1TelefoneEmBranco(nome, keys);
    }

    public String recuperarMsgErroCpf(){
        return BaseTest.getText(msgErroCpf);
    }

    public String recuperarMsgErroTelefone(){
        return BaseTest.getText(msgErroTelefone);
    }

    public String recuperarMsgErroDOB(){
        return BaseTest.getText(msgErroDOB);
    }


    public void popularCamposEscritosEdicaoNome(ArrayList<String> keys) {
        BaseTest.sendKeysPage1EdicaoNome(nome, keys);
    }

    public void popularCamposEscritosEdicaoTelefone(ArrayList<String> keys) {
        BaseTest.sendKeysPage1EdicaoTelefone(nome, keys);
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

    public ArrayList<String> recuperarAtributosPrimeiraPaginaParaEdicao(JSONObject candidatoCriado, String atributoEditado) {
        ObjectMapper objectMapper = new ObjectMapper();
        return Stream.of(candidatoCriado.get(atributoEditado))
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

    public void clicarBotaoProximo() {
        BaseTest.clicarBotaoProximo(nome);
    }

    public void clicarBotaoProximoPosEdicao() {
        BaseTest.clicarBotaoProximoPosEdicao(nome);
    }

}
