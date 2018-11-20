package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;


public class SelBase {
	
	static WebDriver driver;
	

	public SelBase() {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {

	
		this.driver = driver;
	}
	
	
	public void screenCapture(String string) throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File screenShotName = new File( string + ".png");
		FileUtils.copyFile(srcFile,screenShotName);
		Reporter.log("<br><img src='"+screenShotName+"'height='400' width='400' /><br>");
	}
	
	
	

}
