package testbioseguridad;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCrearTresTemas {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    void iniciarSesion() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://bioseguridad.godoycordoba.com/Admin");
        Thread.sleep(2000);  // Espera corta antes de ingresar

        driver.findElement(By.id("TxtUsuario_I")).sendKeys("bioadmin");
        driver.findElement(By.id("TxtClave_I")).sendKeys("B10@dm1n");
        driver.findElement(By.id("BtnIngresar")).click();
        Thread.sleep(2000);  // Espera para que cargue el dashboard
    }

    @Test
    void crearTemas() throws InterruptedException {
        // Ir a la sección "Temas"
        WebElement temasLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Temas")));
        temasLink.click();
        Thread.sleep(2000);

        String[] sectores = {
            "Administrativo y financiero 3",
            "Actividades de impresión y producción de copias a partir de grabaciones originales (CIIU 18)",
            "Actividades de las industrias culturales, radio, televisión y medios de comunicación"
        };

        for (int i = 0; i < sectores.length; i++) {
            // Clic en botón "Nuevo"
            WebElement btnNuevo = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ContentPlaceHolder_GridTema_DXCBtn0")));
            btnNuevo.click();

            // Seleccionar sector
            WebElement btnSector = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ContentPlaceHolder_GridTema_DXEditor1_B-1")));
            btnSector.click();
            Thread.sleep(1000);  // Asegurar que cargue la lista

            WebElement opcionSector = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(text(),'" + sectores[i] + "')]")));
            opcionSector.click();

            // Nombre del tema
            WebElement inputTema = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("ContentPlaceHolder_GridTema_DXEditor2_I")));
            inputTema.clear();
            inputTema.sendKeys("Bebidas para deportes de alto rendimiento " + (i + 1));

            // Subtemas
            WebElement inputSubtema = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("ContentPlaceHolder_GridTema_DXEditor3_I")));
            inputSubtema.clear();
            inputSubtema.sendKeys(String.valueOf((i + 1) * 10)); // Ejemplo: 10, 20, 30

            // Checkbox activo
            WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("ContentPlaceHolder_GridTema_DXEditor4_S_D")));
            checkBox.click();

            // Actualizar
            WebElement btnActualizar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Actualizar')]")));
            btnActualizar.click();

            // Esperar que se guarde y desaparezca el botón de actualización
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Actualizar')]")));
            Thread.sleep(1000);
        }
    }

    @AfterAll
    void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
