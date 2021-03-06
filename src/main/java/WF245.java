import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WF245 {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private String pageUrl = "http://fits.qauber.com/#/page/login";
    private String expectedTitle = "FITS - FITS Web Application";
    private String actualTitle = "";
    private String login = "asads@amail.club";
    private String password = "simon123";
    private String lastName = "Lori";

    private By loginField = By.id("exampleInputEmail1");
    private By passwordField = By.id("exampleInputPassword1");
    private By loginBtn = By.cssSelector("button[type = 'submit']");

    private By mainHeader = By.xpath("//h3[contains(text(),  'Reports')]");
    private By reportHeader = By.xpath("//h3[contains(text(),  'Field Interview Card')]");
    private By textField = By.xpath("/html/body/div[2]/section/div/div[1]/div[7]/div[2]/div/input");
    private By clickClear = By.xpath("/html/body/div[2]/section/div/div[2]/div/button");



    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        System.out.println("\"Clear\" button should clean the text in \"Contains Text\" field");

        driver.get(pageUrl);

        System.out.println("Navigate to Web Page");


        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(passwordField).sendKeys(Keys.ENTER);

        System.out.println("Log in with valid credential");


    }

    @Test
    public void test() throws Exception {

        //   WebElement firstHeader = driver.findElement(mainHeader);
        Thread.sleep(2000);
        driver.findElement(mainHeader).click();

        System.out.println("Click on the \"Report\" located in the left sidebar on the User page");
        System.out.println("User should be redirected to the Report page.");

        driver.findElement(textField).sendKeys(lastName);
        System.out.println("Type text in the \"Contains Text\" field");

        driver.findElement(clickClear).click();
        System.out.println("Press \"Clear\" button");

        TakesScreenshot ts =(TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/fitsWF245.png"));

        System.out.println("Screenshot taken");

        // get the actual value of the title
        actualTitle = driver.getTitle();

        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
