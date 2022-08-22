package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import util.BaseTest;
import util.JsonManipulation;

import java.util.Map;

// Mapeamentos

public class Toasts extends BaseTest {

    private static final By toastRegistradoComSucesso =
            By.cssSelector("#\\32 a23s1g > div.Toastify__toast-body > div:nth-child(2)");
    private final By toastCandidatoVinculadoComSucesso = By.cssSelector("#bsbk0sh");
    public String retornoRegistrar(){
        return BaseTest.getText(toastRegistradoComSucesso);
    }

    public String recuperarMensagemCandidatoVinculadoComSucesso(){
        return BaseTest.getMsgFromToast(toastCandidatoVinculadoComSucesso);
    }

}
