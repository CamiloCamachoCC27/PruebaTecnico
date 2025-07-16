package testbioseguridad;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCrearTresSectores {

    static WebDriver driver;

    @BeforeAll
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

        driver.findElement(By.xpath("//span[text()='Sector']")).click();
        Thread.sleep(1000);
    }

    void crearSector(String nombre, String tema) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Nuevo']")).click();
        Thread.sleep(1000);

        WebElement campoNombre = driver.findElement(By.id("ContentPlaceHolder_GridSector_DXEditor1_I"));
        campoNombre.clear();
        campoNombre.sendKeys(nombre);

        WebElement campoTema = driver.findElement(By.id("ContentPlaceHolder_GridSector_DXEditor2_I"));
        campoTema.clear();
        campoTema.sendKeys(tema);

        WebElement checkActivo = driver.findElement(By.id("ContentPlaceHolder_GridSector_DXEditor3_S_D"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkActivo);

        driver.findElement(By.xpath("//span[text()='Actualizar']")).click();
        Thread.sleep(2000);
    }

    @Test
    void crearTresSectores() throws InterruptedException {
        crearSector("Sector Arrocero", "5");
        crearSector("Sector Cervezero", "9");
        crearSector("Sector Hídraulico 3", "2");
    }

    @AfterAll
    static void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
