package poly.edu;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
		webDriver.findElement(By.name("username")).sendKeys("BuiHuuLoc@gmail.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("fullName")).sendKeys("Bùi Hữu Lộc");
		Thread.sleep(300);
		webDriver.findElement(By.name("email")).sendKeys("BuiHuuLoc@gmail.com");
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
		webDriver.findElement(By.name("email")).sendKeys("BuiHuuLoc@gmail.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("password")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		
	}
	@Test(priority=2)
	public void testCRUD() throws InterruptedException{
		webDriver.findElement(By.name("email")).sendKeys("admin@example.com");
		Thread.sleep(300);
		webDriver.findElement(By.name("password")).sendKeys("123");
		Thread.sleep(300);
		webDriver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(300);
		webDriver.findElement(By.name("products")).click();
		Thread.sleep(300);
		webDriver.findElement(By.name("name")).sendKeys("Điện thoại thông minh");
		Thread.sleep(300);
		webDriver.findElement(By.name("description")).sendKeys("Điện thoại siêu thông minh dẫn đầu xu hướng");
		Thread.sleep(300);
		webDriver.findElement(By.name("price")).sendKeys("500.99");
		Thread.sleep(300);
		webDriver.findElement(By.name("discount")).sendKeys("10");
		Thread.sleep(300);
		WebElement selectElement = webDriver.findElement(By.name("category.categoryID"));
		webDriver.findElement(By.cssSelector("type[submit]")).click();
		Thread.sleep(300);
		Select CartSelect = new Select(selectElement);
		CartSelect.selectByIndex(1);
		Thread.sleep(300);
		// Tìm trong bảng có tên sản phẩm vừa thêm
		String tenSanPham = "Dining Chair L";
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//td[contains(text(), 'Dining Chair L')]")
		));
		WebElement sanPhamMoi = webDriver.findElement(By.xpath("//td[contains(text(), '" + tenSanPham + "')]"));
		Assert.assertTrue(sanPhamMoi.isDisplayed());

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
