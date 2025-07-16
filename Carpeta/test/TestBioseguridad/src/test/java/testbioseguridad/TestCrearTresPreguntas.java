package testbioseguridad;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TestCrearTresPreguntas {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void iniciarSesion() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://bioseguridad.godoycordoba.com/Admin");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtUsuario_I"))).sendKeys("bioadmin");
        driver.findElement(By.id("TxtClave_I")).sendKeys("B10@dm1n");
        driver.findElement(By.id("BtnIngresar")).click();

       
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Preguntas')]"))).click();
    }

    void crearPregunta(String pregunta, String recomendacion) throws InterruptedException {
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Nuevo']"))).click();

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder_GridPregunta_DXEditor1_B-1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'Actividades de impresión')]"))).click();

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder_GridPregunta_DXEditor2_B-1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'Alternativas de organización laboral')]"))).click();

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder_GridPregunta_DXEditor3_B-1"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'Mantenimiento y desinfección')]"))).click();

        
        WebElement campoPregunta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ContentPlaceHolder_GridPregunta_DXEditor4_I")));
        campoPregunta.clear();
        campoPregunta.sendKeys(pregunta);

        
        WebElement campoRecomendacion = driver.findElement(By.id("ContentPlaceHolder_GridPregunta_DXEditor5_I"));
        campoRecomendacion.clear();
        campoRecomendacion.sendKeys(recomendacion);

        
        WebElement checkActivo = driver.findElement(By.id("ContentPlaceHolder_GridPregunta_DXEditor6_S_D"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkActivo);

        
        driver.findElement(By.xpath("//span[text()='Actualizar']")).click();
        Thread.sleep(2000); 
    }

    @Test
    void crearTresPreguntas() throws InterruptedException {
        crearPregunta("¿Cuál es la frecuencia de limpieza de equipos?", "La limpieza debe realizarse antes y después de cada uso.");
        crearPregunta("¿Qué productos se utilizan para la desinfección?", "Se deben emplear desinfectantes autorizados por el INVIMA.");
        crearPregunta("¿Cómo almacenar los productos químicos?", "Se deben almacenar en zonas ventiladas y señalizadas.");
    }

    @AfterAll
    static void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
