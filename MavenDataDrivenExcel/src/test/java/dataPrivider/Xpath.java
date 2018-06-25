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

public class Xpath {
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
	
 
	@Test(dataProvider="Testdata")
	public void FacebookSignUp(String FN, String LN,String EM, String AEM, String PW,String Sex, String MN, String DY,String YR) throws Exception{
		
		driver.get("file:///C:/Users/Admin/OneDrive/Dynamic/OurWebSite.html");
		
		driver.findElement(By.name("FName")).sendKeys(FN);
		driver.findElement(By.name("LName")).sendKeys(LN);
		driver.findElement(By.name("Email")).sendKeys(EM);
		driver.findElement(By.name("AgEmail")).sendKeys(AEM);
		driver.findElement(By.name("PWord")).sendKeys(PW);
		
		if(Sex.equalsIgnoreCase("Male")){
			driver.findElement(By.xpath("//input[7]")).click();
		}
		else if(Sex.equalsIgnoreCase("Female")){
			driver.findElement(By.xpath("//input[8]")).click();
		}
		else{
			System.out.println("Wrong Object Type");
			
		}
		driver.findElement(By.id("MN")).sendKeys(MN);
		driver.findElement(By.id("DY")).sendKeys(DY);
		driver.findElement(By.id("YR")).sendKeys(YR);
		Thread.sleep(3000);
		
	}


@DataProvider(name="Testdata")
public Object[][] getDataFromDataprovider() throws IOException{
	
    Object[][] object = null;
    File file = new File("E:\\COMPANY_DATA\\FacebookData.xlsx");
    FileInputStream fis=new FileInputStream(file);
	Workbook wb =  new XSSFWorkbook(fis);
    Sheet ws = wb.getSheet("Data2");

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
