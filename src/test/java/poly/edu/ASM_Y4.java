package poly.edu;


import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;







public class ASM_Y4 {
	WebDriver webDriver;
	@BeforeClass
	public void setup() throws InterruptedException  {
		webDriver = new ChromeDriver();
		webDriver.get("http://localhost:8080/account/login");
		
		if (isLoggedIn()) {
            System.out.println("Người dùng đã đăng nhập, tiến hành đăng xuất trước khi test...");
            logout();
        }
	}
	@Test(priority= 1)
	public void testRegister() throws InterruptedException {
		webDriver.findElement(By.name("register")).click();
		Thread.sleep(300);
		webDriver.findElement(By.name("username")).sendKeys("BuiHuuLoc1@gmail.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("fullName")).sendKeys("Bùi Hữu Lộc");
		Thread.sleep(300);
		webDriver.findElement(By.name("email")).sendKeys("BuiHuuLoc1@gmail.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("phone")).sendKeys("0763288770");
		Thread.sleep(300);
		webDriver.findElement(By.name("address")).sendKeys("7b hiệp thành 17");
		Thread.sleep(300);
		webDriver.findElement(By.name("password")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.name("confirmPassword")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.xpath("//a[contains(text(), 'Đăng Ký')]")).click();
		Thread.sleep(300);
		webDriver.findElement(By.name("login")).click();
		Thread.sleep(300);
		webDriver.findElement(By.name("email")).sendKeys("BuiHuuLoc1@gmail.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("password")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		
	}
	@Test(priority=2)
	public void testCRUDUsers() throws InterruptedException{
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
		webDriver.findElement(By.cssSelector("a[href='/users/delete/40']")).click();
		// Chuyển sang alert
		Alert alert = webDriver.switchTo().alert();

		// Nhấn OK (chấp nhận alert)
		alert.accept();
	}
	private boolean isLoggedIn() {
        return webDriver.getCurrentUrl().equals("http://localhost:8080/user/products") ||
                isElementPresent(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
    }

    // Đăng xuất nếu đang đăng nhập
    private void logout() throws InterruptedException {
        try {
            WebElement logoutButton = webDriver.findElement(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
            logoutButton.click();
            System.out.println("Đã đăng xuất thành công!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy nút Đăng Xuất.");
        }
    }
    private boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
        	webDriver.quit();
        }
    }
}
