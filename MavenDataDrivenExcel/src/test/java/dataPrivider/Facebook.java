package dataPrivider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Facebook {
	
	WebDriver driver;
	@BeforeTest
	public void setup(){
		//System.setProperty("webdriver.gecko.driver", "F:\\All Driver\\geckodriver.exe");
		//driver= new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", "E:\\All Driver\\chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	@AfterTest
	public void closeBrowser(){
		driver.close();
		driver.quit();
	}
	
 
	@Test(dataProvider="Facebookdata")
	public void FacebookSignUp(String FN, String LN,String EM, String AEM, String PW, String MN, String DY,String YR, String Sex){
		
		driver.get("https://www.facebook.com/");
		driver.navigate().refresh();
		
		driver.findElement(By.name("firstname")).sendKeys(FN);
		driver.findElement(By.name("lastname")).sendKeys(LN);
		driver.findElement(By.name("reg_email__")).sendKeys(EM);
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys(AEM);
		driver.findElement(By.name("reg_passwd__")).sendKeys(PW);
		driver.findElement(By.id("month")).sendKeys(MN);
		driver.findElement(By.id("day")).sendKeys(DY);
		driver.findElement(By.id("year")).sendKeys(YR);
		
		if(Sex.equalsIgnoreCase("Male")){
			driver.findElement(By.id("u_0_a")).click();
		}
		else if(Sex.equalsIgnoreCase("Female")){
			driver.findElement(By.id("u_0_9")).click();
		}		
		
		//driver.findElement(By.cssSelector("#u_0_h")).click();
		
	}


@DataProvider(name="Facebookdata")
public Object[][] getDataFromDataprovider() throws IOException{
	
    Object[][] object = null;
    
    File file = new File("E:\\CompanyData\\FacebookData.xlsx");
    FileInputStream fis=new FileInputStream(file);
	Workbook wb =  new XSSFWorkbook(fis);
    Sheet ws = wb.getSheet("Data1");

    int rowCount = ws.getLastRowNum()- ws.getFirstRowNum();
    int colCount=9;
    object = new Object[rowCount][colCount];
    
    for (int i = 0; i <rowCount; i++) {
        
        Row row = ws.getRow(i+1);
        
        for (int j = 0; j < row.getLastCellNum(); j++) {
            
            object[i][j] = row.getCell(j).toString();
        }
    }
    	
    	return object;    
}

	
	

}
