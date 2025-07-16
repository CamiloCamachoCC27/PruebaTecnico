package testbioseguridad;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3 {

    @Test
    void crearTresEvaluaciones() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        // Crear configuración de Chrome con perfil limpio
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/temp/chrome-profile-test"); // Cambia a un path temporal válido
        options.addArguments("--no-first-run");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");

        // Desactivar gestor de contraseñas explícitamente
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://bioseguridad.godoycordoba.com/");
        Thread.sleep(2000);

        // Login
        driver.findElement(By.id("TxtUsuario_I")).sendKeys("testqa@godoycordoba.com");
        driver.findElement(By.id("TxtClave_I")).sendKeys("12345");
        WebElement botonIngresar = driver.findElement(By.id("BtnIngresar"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botonIngresar);
        botonIngresar.click();
        Thread.sleep(3000);

        // Crear 3 evaluaciones
        for (int i = 1; i <= 3; i++) {
            WebElement btnNueva = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnPago")));
            btnNueva.click();

            WebElement btnEvaluar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Evaluar']")));
            btnEvaluar.click();

            for (int j = 0; j < 3; j++) {
                WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("ContentPlaceHolder_GridAuditoria_DXEditor4_B-1")));
                dropdown.click();

                String respuestaId = (j + i) % 2 == 0 ? 
                    "ContentPlaceHolder_GridAuditoria_DXEditor4_DDD_L_LBI0T0" : 
                    "ContentPlaceHolder_GridAuditoria_DXEditor4_DDD_L_LBI1T0";

                WebElement respuesta = wait.until(ExpectedConditions.elementToBeClickable(By.id(respuestaId)));
                respuesta.click();

                WebElement campo = driver.findElement(By.id("ContentPlaceHolder_GridAuditoria_DXEditor4_I"));
                campo.sendKeys(Keys.TAB);
                Thread.sleep(1000);
            }

            WebElement btnFinalizar = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ContentPlaceHolder_btnFinalizar_CD")));
            btnFinalizar.click();
            Thread.sleep(2000);

            WebElement btnPDF = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '.pdf')]")));
            btnPDF.click();

            Thread.sleep(3000);
        }

        driver.quit();
    }
}
