package testbioseguridad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class TestCrearTresSubtemas {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        iniciarSesion();
        crearSubtema();
        driver.quit();
    }

    static void iniciarSesion() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://bioseguridad.godoycordoba.com/Admin");
        Thread.sleep(2000);

        driver.findElement(By.id("TxtUsuario_I")).sendKeys("bioadmin");
        driver.findElement(By.id("TxtClave_I")).sendKeys("B10@dm1n");
        driver.findElement(By.id("BtnIngresar")).click();
        Thread.sleep(2000);
    }

    static void crearSubtema() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ir a Subtemas
        WebElement subtemasLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Subtemas']")));
        subtemasLink.click();
        Thread.sleep(2000);

        // Botón Nuevo
        WebElement btnNuevo = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Nuevo']")));
        btnNuevo.click();

        // Seleccionar sector
        WebElement btnSector = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("ContentPlaceHolder_GridSubtema_DXEditor1_B-1")));
        btnSector.click();
        Thread.sleep(1000);

        WebElement sectorOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(),'Aerolíneas y explotadores de aeronave')]")));
        sectorOption.click();

        // Seleccionar tema
        WebElement btnTema = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("ContentPlaceHolder_GridSubtema_DXEditor2_B-1")));
        btnTema.click();
        Thread.sleep(1000);

        WebElement temaOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//td[contains(text(),'Generales')]")));
        temaOption.click();

        // Ingresar subtema
        WebElement inputSubtema = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("ContentPlaceHolder_GridSubtema_DXEditor3_I")));
        inputSubtema.clear();
        inputSubtema.sendKeys("101");

        // Ingresar número de preguntas
        WebElement inputPreguntas = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("ContentPlaceHolder_GridSubtema_DXEditor4_I")));
        inputPreguntas.clear();
        inputPreguntas.sendKeys("7");

        // Activar checkbox
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("ContentPlaceHolder_GridSubtema_DXEditor5_S_D")));
        checkBox.click();

        // Botón Actualizar
        WebElement btnActualizar = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[text()='Actualizar']")));
        btnActualizar.click();

        // Confirmar actualización
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//span[text()='Actualizar']")));
        Thread.sleep(1000);
    }
}
