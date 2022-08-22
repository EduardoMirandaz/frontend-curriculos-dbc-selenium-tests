    package pages.CandidatosPages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import util.BaseTest;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


    public class RegistroExperienciasCandidatoPage {

        public final By instituicao = By.cssSelector("#instituicao");
        public final By cargo = By.cssSelector("#cargo");

        public void popularCamposEscritos(ArrayList<String> keys) {
            BaseTest.sendKeysPage4(instituicao, keys);
        }

        public void clicarBotaoProximo() {
            BaseTest.clicarBotaoProximo(cargo);
        }

        public ArrayList<String> recuperarAtributosExperiencias(JSONObject candidatoCriado) {
            ObjectMapper objectMapper = new ObjectMapper();

            ArrayList<JSONObject> experiencias = (ArrayList<JSONObject>) candidatoCriado.get("experiencias");
            JSONObject experiencia = experiencias.get(0);


            return Stream.of(
                            experiencia.get("instituicao"),
                            experiencia.get("cargo"),
                            recuperarData(experiencia.get("dataInicio")),
                            recuperarData(experiencia.get("dataFim")),
                            experiencia.get("descricao"))
                    .map(e -> objectMapper.convertValue(e, String.class))
                    .collect(Collectors.toCollection(ArrayList::new));

        }

        private Object recuperarData(Object dataNascimento) {
            String[] dataList = dataNascimento.toString().split("-");
            return dataList[2] + dataList[1] +dataList[0];
        }

    }
