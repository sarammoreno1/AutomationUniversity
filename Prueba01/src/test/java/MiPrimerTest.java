import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MiPrimerTest {

    //Aquí instanciamos un objeto de la Interfaz WebDriver
    private WebDriver driver;
    //Aquí a la variable titulo le decimos que es un elemento Web tipo Xpath y le damos el valor que lo identifica en el navegador
    By titulo = By.xpath("//div//h2//span[contains(text(),'Automatización')]");

    //Anotaciones que puedo configurar para correr siempre antes de cada prueba los mismo elementos o acciones
    @Before
    public void setUp(){
       //Aquí indicamos que vamos a trabajar con el controlador de Chrome y le damos la ubicación
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        //al objeto instanciado de la Interfaz WebDriver le indicamos que va a trabajar con el controlador de Chrome
        driver = new ChromeDriver();
        //Aquí maximizamos la ventana
        driver.manage().window().maximize();
        //Aquí le indicamos a que url queremos ingresar
        driver.navigate().to("https://www.google.com//");
    }

    //Casos de prueba que ejecutaremos
    @Test
    public void verificarTitulo() throws InterruptedException {
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.clear();
        Thread.sleep(2000);
        searchbox.sendKeys("Automatización");
        Thread.sleep(2000);
        searchbox.submit();
        //Espera 2 segundos a que se ejecute la acción antes de fallar la prueba.
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(titulo).getText().contains("Automatización");
        //Validación del titulo de la pestaña sea igual a lo enviado
        Assert.assertEquals("Automatización - Buscar con Google", driver.getTitle());
        //Validación de que en un título aparece la palabra
        Assert.assertTrue("Automatización es visible en la información de Wikipedia",driver.getTitle().contains("Automatización"));
    }

    //Anotaciones que puedo configurar para correr siempre después de cada prueba los mismos elementos o acciones
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
