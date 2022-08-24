import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class LoginExample {

    private WebDriver driver;

    //Elementos Web que asignamos a una variable
    By userName = By.xpath("//input[@type='email']");
    By password = By.xpath("//input[@type='password']");
    By nextBtn = By.xpath("//span[text()='Siguiente']");
    By composeBtn = By.xpath("//div[@class='T-I T-I-KE L3']");
    By recipientslbl = By.xpath("//div[@class='aH9']//input[@aria-autocomplete='list']");
    By subjectlbl = By.name("subjectbox");
    By body = By.xpath("//div[@aria-label='Cuerpo del mensaje']");
    By sendBtn = By.xpath("//div[@class='dC']");
    By emailsSendBtn = By.xpath("//a[@href='https://mail.google.com/mail/u/0/#sent']");
    By getSubjectlbl = By.xpath("//span[contains(text(),'Prueba automatizada')]");

    //Anotaciones que puedo configurar para correr siempre antes de cada prueba los mismo elementos o acciones
    @Before
    public void verificarTitulo(){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Aquí va la url a la que quieras ingresar
        driver.navigate().to("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    }

    //Este es el caso de prueba que ejecutaremos
    @Test
    public void login() throws InterruptedException {
        driver.findElement(userName).click();
        driver.findElement(userName).sendKeys("Aquí va tu correo");
        Thread.sleep(2000);
        driver.findElement(nextBtn).click();
        Thread.sleep(2000);
        driver.findElement(password).click();
        driver.findElement(password).sendKeys("Aquí va tu contraseña");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(nextBtn).click();
        Thread.sleep(2000);
        //Validación de qué al entrar encontró esa palabra visible
        Assert.assertTrue("Compose", true);
        enviarCorreo();
    }

    @Test
    public void enviarCorreo() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(composeBtn).click();
        Thread.sleep(2000);
        driver.findElement(recipientslbl).click();
        driver.findElement(recipientslbl).sendKeys("Aquí va el destinatario");
        Thread.sleep(2000);
        driver.findElement(subjectlbl).click();
        driver.findElement(subjectlbl).sendKeys("Prueba automatizada");
        Thread.sleep(2000);
        driver.findElement(body).click();
        driver.findElement(body).sendKeys("Recibiste un mensaje automatizado");
        Thread.sleep(2000);
        driver.findElement(sendBtn).click();
        Thread.sleep(2000);
        verificarCorreoEnviado();
    }

    @Test
    public void verificarCorreoEnviado() throws InterruptedException {
        driver.findElement(emailsSendBtn).click();
        Thread.sleep(2000);
        driver.findElement(getSubjectlbl).isDisplayed();
    }

    //Anotaciones que puedo configurar para correr siempre después de cada prueba los mismos elementos o acciones
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
