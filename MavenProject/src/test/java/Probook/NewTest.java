package Probook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class NewTest {
	// Déclaration des variables que nous allons utiliser dans ce script.
		WebDriver driver;
		String url1 = "https://probook.progideo.com";
		String url2 = "https://www.testingtoolsguide.net";
		String expectedLoginFullName1 = "oussama zarrok";
		WebElement actualLoginFullName1 = null;
		//String username = "oussama";
		//String password = "Afraid742";
		String expectedLoginFullName2 = "oussama zarrok";
		WebElement actualLoginFullName2 = null;
  @DataProvider (name="Cas_passant")
  public static Object[][] Cas_passant() {
	  return new Object[][] {{ "oussama" , "Afraid742"}, {"oussama", "Afraid742"}};
  }
  @Test (dataProvider="Cas_passant")
  
  public void Se_connecter(String username, String password) throws InterruptedException {
	  	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-enter")));
	  	driver.findElement(By.className("btn-enter")).click();
		driver.switchTo().activeElement();
		Thread.sleep(2000);
		// On saisit le username et le password
		driver.findElement(By.id("login_username")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_username")));
		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);
		// On clique sur le bouton "Sign in"
		driver.findElement(By.id("login-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-dropdown-link")));
		// On récupère le titre de la page actuelle
		actualLoginFullName1 = driver.findElement(By.id("account-dropdown-link"));
		// On affiche dans le log un message d'information
		System.out.println("Login page title : expected value is "+expectedLoginFullName1+" actual value is "+actualLoginFullName1.getText());
		// Si le test échoue, on affiche un message d'erreur
		
		Assert.assertTrue(actualLoginFullName1.getText().contentEquals(expectedLoginFullName1), "Test Succeded");
		//Assert.assertFalse(actualLoginFullName1.getText().contentEquals(expectedLoginFullName1), "Test Succeded");
					/*if (!actualLoginFullName1.getText().contentEquals(expectedLoginFullName1)) {
						System.out.println("Test Failed");
					}
					// On vérifie le nom d'utilisateur connecté
						actualLoginFullName2 = driver.findElement(By.className("user-title"));
					// On affiche dans le log un message d'information
						System.out.println("Login result : expected value is "+expectedLoginFullName2+" actual value is "+actualLoginFullName2.getText());
					// Si le test échoue, on affiche un message d'erreur
					if (!actualLoginFullName2.getText().contentEquals(expectedLoginFullName2)) {
						System.out.println("Test Failed");
					}
					else {
						System.out.println("Test Succeded");
					}*/
  }
  
  //@Parameters ("browser")
  @BeforeMethod
  public void beforeMethod() {
	  String browser = "firefox";
	  			if (browser.equalsIgnoreCase("firefox")) {
	  				System.setProperty("webdriver.gecko.driver", "D:\\eclipse\\drivers\\geckodriver\\geckodriver.exe");
	  				driver = new FirefoxDriver();
	  			} else if (browser.equalsIgnoreCase("chrome")) {
	  				System.setProperty("webdriver.chrome.driver", "D:\\eclipse\\drivers\\chromedriver\\chrome-win64\\chrome.exe");
	  				driver = new ChromeDriver();
	  			} else {
	  				System.setProperty("webdriver.edge.driver","D:\\eclipse\\drivers\\edgedriver_win32\\msedgedriver.exe");
					// Invocation du navigateur Firefox, qui sera identifié avec le nom "driver".
					driver = new EdgeDriver();
	  			}

				// Chemin vers le driver Gecko (pour Firefox uniquement)
				// System.setProperty("webdriver.edge.driver","D:\\eclipse\\drivers\\edgedriver_win32\\msedgedriver.exe");
				// Invocation du navigateur Firefox, qui sera identifié avec le nom "driver".
				// driver = new EdgeDriver();
				// Ouvrir la page "http://probook.progideo.com".
				driver.get(url1);
				
				driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
