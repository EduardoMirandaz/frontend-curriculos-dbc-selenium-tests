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
    private final By fieldEmail = By.cssSelector("#email");
    private final By fieldSenha = By.cssSelector("#senha");
    private final By fieldConfirmarSenha = By.cssSelector("#confirmaSenha");

    public String retornoRegistrar(){
        return BaseTest.getText(toastRegistradoComSucesso);
    }

}
