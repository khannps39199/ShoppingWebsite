package poly.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.Console;
import java.time.Duration;
import java.util.List;

public class ASM_CART_TESTCASE {
    private WebDriver driver;
    private String baseUrl = "http://localhost:8080"; // Cập nhật URL của bạn

    @BeforeTest()
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void testAddToCart() {
        driver.get(baseUrl + "/account/login");
        login("michael@example.com", "mnp456hashed");
        driver.get(baseUrl + "/user/products");
        driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
        driver.get(baseUrl + "/cart");
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item-tocount"));
        System.out.println(cartItems.size());
        Assert.assertTrue(cartItems.size() > 0, "Sản phẩm không được thêm vào giỏ hàng!");
    }

    @Test(priority = 2)
    public void testUpdateCartQuantity() {
        driver.get(baseUrl + "/cart");
        WebElement quantityInput = driver.findElement(By.name("quantity"));
        quantityInput.clear();
        quantityInput.sendKeys("3");
        driver.findElement(By.id("update-cart-btn")).click();
        WebElement updatedQuantity = driver.findElement(By.name("quantity"));
        Assert.assertEquals(updatedQuantity.getAttribute("value"), "3", "Cập nhật số lượng thất bại!");
    }

    @Test(priority = 3)
    public void testApplyDiscount() {
        driver.get(baseUrl + "/cart");
        WebElement voucherInput = driver.findElement(By.id("voucher"));
        voucherInput.sendKeys("DISCOUNT10");
        driver.findElement(By.id("apply-voucher")).click();
        WebElement discountMessage = driver.findElement(By.id("discount-message"));
        Assert.assertTrue(discountMessage.getText().contains("Giảm giá được áp dụng"), "Mã giảm giá không hợp lệ!");
    }

    @Test(priority = 4)
    public void testRemoveFromCart() {
        driver.get(baseUrl + "/cart");
        driver.findElement(By.id("remove-product-btn")).click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));
        Assert.assertEquals(cartItems.size(), 0, "Xóa sản phẩm thất bại!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void login(String username, String password) {
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btn-login")).click();
    }
}

