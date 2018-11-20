package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class WebTest  {

	SelBase selBase = new SelBase();
	WebDriver driver ;
	WebElement element;
	static String chromeDrivePath = "/Users/darya1/Documents/jarFolder/chromedriver";
	String filePath ="/Users/darya1/Documents/production/";
	String fileName ="ShipmentDetails.xlsx";
	String url="http://apps.qa2qe.cognizant.e-box.co.in/ShipmentCharge/Index";
	ReadExcel excel1 = new ReadExcel();
	Object[][] testData;
	Select drpOriginPort;
	Select drpDestinationPort;
	



	
	

	
	@BeforeMethod()
	public void driverSetUp() {
		System.setProperty("webdriver.chrome.driver",chromeDrivePath);
		selBase.setDriver(new ChromeDriver());
		driver = selBase.getDriver();

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        
        
	}
	
	@DataProvider
	public Object[][] shipmentData() {
		excel1.readExcel(filePath,fileName,"data");
		
		testData = excel1.getSheetData();
        return (testData);
	}
	
	@Test(dataProvider="shipmentData")
	public void testShipment(String orginPort,String destinationPort,String railModeCharge,String roadModeCharge,String airModeCharge) throws IOException {
		
		if(orginPort.equals("Mumbai") && destinationPort.equals("New York") ) {
			 drpOriginPort=getDropDownValue("origin_id");
			 drpOriginPort.selectByVisibleText(orginPort);
			 drpOriginPort=getDropDownValue("destination_id");
			 drpOriginPort.selectByVisibleText(destinationPort);
			 element=driver.findElement(By.name("submit"));
			 element.submit();
			 selBase.screenCapture("newyork");
			 //assert road value
			 String road = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();
			 String roadValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]")).getText();
			 assertEquals(roadModeCharge,roadValue);
			 
			 String rail = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]")).getText();
			 String railValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]")).getText();
			 assertEquals(railModeCharge,railValue);
			 
			 String air = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]")).getText();
			 String airValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[2]")).getText();
			 assertEquals(airModeCharge,airValue);
			
		}else if(orginPort.equals("Mumbai") && destinationPort.equals("Cochin")) {
			
			 drpOriginPort=getDropDownValue("origin_id");
			 drpOriginPort.selectByVisibleText(orginPort);
			 drpOriginPort=getDropDownValue("destination_id");
			 drpOriginPort.selectByVisibleText(destinationPort);
			 element=driver.findElement(By.name("submit"));
			 element.submit();
			 selBase.screenCapture("Cochin");
			 String rail = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();
			 String railValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]")).getText();
			 assertEquals(railModeCharge,railValue);
			 
			 String air = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]")).getText();
			 String airValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]")).getText();
			 assertEquals(airModeCharge,airValue);
			 
			 //assert road value
			 String road = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]")).getText();
			 String roadValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[2]")).getText();
			 assertEquals(roadModeCharge,roadValue);
			 
			
			 
			
		}else if(orginPort.equals("Cochin") && destinationPort.equals("Farah")) {
			
			 drpOriginPort=getDropDownValue("origin_id");
			 drpOriginPort.selectByVisibleText(orginPort);
			 drpOriginPort=getDropDownValue("destination_id");
			 drpOriginPort.selectByVisibleText(destinationPort);
			 element=driver.findElement(By.name("submit"));
			 element.submit();
			 selBase.screenCapture("Farah");
			 String rail = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();
			 String railValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]")).getText();
			 assertEquals(railModeCharge,railValue);
			 
			 String road = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]")).getText();
			 String roadValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[2]")).getText();
			 assertEquals(roadModeCharge,roadValue);
			 
			 //assert road value
			 String air = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]")).getText();
			 String airValue = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[2]")).getText();
			 
			 assertEquals(airModeCharge,airValue);
			 
			
			 
			
		}else {
			 selBase.screenCapture("homepage");
		 }
	
		
	}
	
	@AfterMethod()
	public void setCloseDriver() {
		
		driver.quit();
	}
	
	public Select getDropDownValue(String elementName) {
		drpOriginPort =new Select(driver.findElement(By.name(elementName)));
		return drpOriginPort;
	}
	
	
	

}
