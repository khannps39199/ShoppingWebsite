package poly.edu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class ASM_CRUDUSER_TESTCASE {
	 WebDriver webDriver;
	    Workbook workbook;
	    Sheet sheet;
	    int rowCount = 0;
	    private static final String FILE_PATH = "C:\\Users\\nkha7\\Documents\\workspace-spring-tool-suite-4-4.27.0.RELEASE\\PS39199_NguyenNhutKha_Assignment\\TestResults.xlsx";

	@BeforeClass
    public void setup() throws InterruptedException {
		webDriver = new ChromeDriver();
		webDriver.get("http://localhost:8080/account/login");
        
    }
//	@Test( priority = 1)
//    public void testArrayListUser(String email, String password, boolean expectedResult, String expectedMessage) throws InterruptedException {
//		driver.get("http://localhost:8080/admin/getUser?page=0&size=5");
//        boolean ArrayListExists = isElementPresent(By.xpath("//a[contains(text(), 'Sửa')]"));
//        System.out.println("Sửa link present: " + ArrayListExists);
//        Assert.assertTrue(ArrayListExists, expectedMessage);
//
//    }
//    @Test(dataProvider = "loginData",priority = 2)
//    public void testLogin(String Username, String password,String fullname,String email, boolean expectedResult, String expectedMessage) throws InterruptedException {
//    	driver.get("http://localhost:8080/admin/getUser?page=0&size=5");
//    	WebElement username = driver.findElement(By.id("username"));
//    	WebElement pass = driver.findElement(By.id("passwordHash"));
//    	WebElement fulname = driver.findElement(By.id("fullname"));
//    	WebElement emailField = driver.findElement(By.id("email"));
//
//    	username.sendKeys(Username);Thread.sleep(1000);
//    	pass.sendKeys(password);Thread.sleep(1000);
//    	fulname.sendKeys(fullname);Thread.sleep(1000);
//    	emailField.sendKeys(email);Thread.sleep(1000);
//        
//
//    }
	
	@Test(priority=2)
	public void testCRUDUsers(String Username, String password,String fullname,String email, boolean expectedResult, String expectedMessage) throws InterruptedException{
		webDriver.findElement(By.name("email")).sendKeys("admin@example.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("password")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(500);
		webDriver.findElement(By.cssSelector("a[href='/admin/getUser']")).click();
		Thread.sleep(500);
		webDriver.findElement(By.id("username")).sendKeys("LochuuBui");
		Thread.sleep(500);
		webDriver.findElement(By.id("passwordHash")).sendKeys("123");
		Thread.sleep(500);
		webDriver.findElement(By.id("fullName")).sendKeys("Bùi Hữu Lộc");
		Thread.sleep(500);
		webDriver.findElement(By.id("email")).sendKeys("Loclocloc@gmail.com");
		Thread.sleep(500);
		Select select = new Select(webDriver.findElement(By.id("role")));
		select.selectByValue("Admin");
		Thread.sleep(500);
		//sao khi an vao submit se la phan tu cuoi cung va nam o trang 7
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		webDriver.findElement(By.cssSelector("a[href='/admin/getUser?page=6&size=5']")).click();
		Thread.sleep(700);
		webDriver.findElement(By.cssSelector("a[href='/users/edit/40?page=6&size=5']")).click();
		Thread.sleep(700);
		webDriver.findElement(By.id("username")).clear();
		webDriver.findElement(By.id("username")).sendKeys("aaaaaaaa");
		Thread.sleep(700);
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(700);
		webDriver.findElement(By.cssSelector("a[href='/users/delete/"+""+ "']")).click();
		// Chuyển sang alert
		Alert alert = webDriver.switchTo().alert();

		// Nhấn OK (chấp nhận alert)
		alert.accept();
	}
	 @DataProvider(name = "loginData")
	    public Object[][] data() {
	        return new Object[][]{
	                {"john@example.com", "abcdef123hashed", true, ""},   // Đăng nhập thành công
	                {"michael@example.com", "mnp456hashed", true, ""},   // Đăng nhập thành công
	                {"jane@example.com", "xyz987hashed", true, ""},   // Đăng nhập thành công
	                {"invalidUser", "abcdef123hashed", false, "Email không tồn tại!"}, // Sai username
	                {"john@example.com", "invalidPass", false, "Mật khẩu không đúng!"}, // Sai password
	                {"", "abcdef123hashed", false, "Email không tồn tại!"},            // Username rỗng
	                {"john@example.com", "", false, ""}             // Password rỗng
	        };
	    }
   

    private boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", true, "Đã đăng nhập với tài khoản admin"},   // Đăng nhập thành công
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", true, ""},   // Đăng nhập thành công
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", true, ""},   // Đăng nhập thành công
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, "Email không tồn tại!"}, // Sai username
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, "Mật khẩu không đúng!"}, // Sai password
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, "Email không tồn tại!"},            // Username rỗng
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, ""}  ,            // Username rỗng
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, ""}  ,            // Username rỗng
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, ""}  ,            // Username rỗng
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, ""}  ,            // Username rỗng
                {"khannps39199", "abcdef123hashed", "NGUYENNHUTKHA", "khannps39199@gmail.com", false, ""}             // Password rỗng
        };
    }


    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
