package PCCentralSuite;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

public class SigningUp {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "lib/mac/chromedriver");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testSigningUp() throws Exception {
    driver.get("http://ec2-3-139-67-4.us-east-2.compute.amazonaws.com:8080/webproject/homePage.html");
    driver.findElement(By.id("loginORlogout")).click();
    driver.get("http://ec2-3-139-67-4.us-east-2.compute.amazonaws.com:8080/webproject/loginOrSignUp.html");
    driver.findElement(By.xpath("//form[2]/input")).click();
    driver.findElement(By.xpath("//form[2]/input")).clear();
    driver.findElement(By.xpath("//form[2]/input")).sendKeys("user1");
    driver.findElement(By.xpath("//form[2]/input[2]")).click();
    driver.get("http://ec2-3-139-67-4.us-east-2.compute.amazonaws.com:8080/webproject/AddUsernameIfValid");
    driver.get("http://ec2-3-139-67-4.us-east-2.compute.amazonaws.com:8080/webproject/loginOrSignUp.html");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
