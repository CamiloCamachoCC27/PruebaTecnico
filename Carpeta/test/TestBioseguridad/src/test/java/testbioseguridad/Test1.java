package testbioseguridad;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

    @Test
    void buscarTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://bioseguridad.godoycordoba.com/");
        Thread.sleep(2000);

        WebElement botonIngresar = wait.until(ExpectedConditions.elementToBeClickable(By.id("BtnIngresar")));
        js.executeScript("arguments[0].scrollIntoView(true);", botonIngresar);

        WebElement usuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtUsuario_I")));
        usuario.sendKeys("biousuarionuevo");

        WebElement contraseña = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtClave_I")));
        contraseña.sendKeys("abc123");

        Thread.sleep(2000);
        botonIngresar.click();

        Thread.sleep(2000);
        WebElement botonRegistrate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar']/div/p/a")));
        botonRegistrate.click();

        Thread.sleep(2000);
        WebElement razonSocial = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtNombre_I")));
        razonSocial.sendKeys("biousuarionuevo2");

        Thread.sleep(2000);
        WebElement campoNit = wait.until(ExpectedConditions.elementToBeClickable(By.id("TxtNit_I")));
        campoNit.click();
        campoNit.clear();
        campoNit.sendKeys("900876549");
        campoNit.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        WebElement campoSector = wait.until(ExpectedConditions.elementToBeClickable(By.id("LstSector_I")));
        campoSector.clear();
        campoSector.sendKeys("Caficultor");
        campoSector.sendKeys(Keys.TAB);

        Thread.sleep(2000);
        WebElement telefono = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtTelefono_I")));
        telefono.sendKeys("3206554432");

        Thread.sleep(2000);
        WebElement correo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtCorreo_I")));
        correo.sendKeys("testnuevousuariodos@gmail.com");

        Thread.sleep(2000);
        WebElement clave = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TxtClave_I")));
        clave.sendKeys("abcdefg123456");

        Thread.sleep(2000);
        WebElement botonRegistrar = wait.until(ExpectedConditions.elementToBeClickable(By.id("BtnRegistrar")));
        js.executeScript("arguments[0].scrollIntoView(true);", botonRegistrar);
        botonRegistrar.click();

        Thread.sleep(4000); // Espera final para observar resultado

        driver.quit();
    }
}
