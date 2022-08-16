package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonManipulation extends Browser{

    public static void criarJsonCadastro(Map<String, String> login){
        // caminho da massa de dados

        String pathJson = "src/test/resources/loginCriado.json";

        JSONObject file = new JSONObject();
        file.put("email", login.get("email"));
        file.put("password", login.get("password"));

        try (PrintWriter out = new PrintWriter(new FileWriter(pathJson))) {
            out.write(file.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> recuperarCadastro(){
        // caminho da massa de dados

        String pathJson = "src/test/resources/loginCriado.json";

        // Recuperando o usuario completo em formato de json
        JSONObject jsonObject = recuperarUserPeloJSON(pathJson);
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");

        Map<String, String> login = new HashMap<>();
        login.put("email", email);
        login.put("password", password);

        return login;

    }

    private static JSONObject recuperarUserPeloJSON(String jsonPath){
        Object parse = null;
        try {
            parse = JSONValue.parse(new FileReader(
                    jsonPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Converte o JSONParser para JSONObject
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(parse, JSONObject.class);
    }



}
