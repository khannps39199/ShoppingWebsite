package poly.edu;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class ASM_Resgter_TESTCASE {
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/account/register");

        if (isLoggedIn()) {
            System.out.println("Người dùng đã đăng nhập, tiến hành đăng xuất...");
            logout();
        }
    }

    @Test(dataProvider = "registerData")
    public void testRegister(String username, String email, String password, String confirmPassword, String fullName,
                             String phone, String address, boolean expectedSuccess, String expectedMessage, String testCaseId) throws InterruptedException {
        driver.get("http://localhost:8080/account/register");
        Thread.sleep(2000);

        if (isLoggedIn()) {
            Assert.fail("[BLOCKED] Người dùng đã đăng nhập, không thể test đăng ký! (" + testCaseId + ")");
        }

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
        driver.findElement(By.name("fullName")).sendKeys(fullName);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("address")).sendKeys(address);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[contains(text(), 'Đăng Ký')]")).click();
        Thread.sleep(2000);

        boolean isSuccess = driver.getCurrentUrl().equals("http://localhost:8080/user/products");
        String actualMessage = getErrorMessage();

        if (expectedSuccess) {
            Assert.assertTrue(isSuccess, "[FAILED] Test Case " + testCaseId + ": Không chuyển đến trang /user/products");
            System.out.println("[PASSED] Test Case " + testCaseId + ": Đăng ký thành công!");
            logout();
        } else {
            Assert.assertFalse(isSuccess, "[FAILED] Test Case " + testCaseId + ": Đáng lẽ phải có lỗi nhưng lại chuyển hướng!");
            Assert.assertTrue(actualMessage.contains(expectedMessage),
                "[FAILED] Test Case " + testCaseId + ": Lỗi không đúng! Nhận được: " + actualMessage);
            System.out.println("[PASSED] Test Case " + testCaseId + ": Đăng ký thất bại đúng như mong đợi.");
        }
        logout();
        Thread.sleep(1000);
    }

    private String getErrorMessage() {
        try {
            WebElement errorElement = driver.findElement(By.className("alert-danger"));
            return errorElement.getText();
        } catch (Exception e) {
return "";
        }
    }

    private boolean isLoggedIn() {
        return isElementPresent(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
    }

    private void logout() throws InterruptedException {
        try {
            WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
            logoutButton.click();
            Thread.sleep(2000);
            System.out.println("Đã đăng xuất thành công!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy nút Đăng Xuất.");
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DataProvider(name = "registerData")
    public Object[][] registerData() {
        return new Object[][]{
            {"newuser1", "newuser1@gmail.com", "password123", "password123", "Nguyen Van A", "0123456789", "Hanoi", true, "", "TC_REGISTER_001"},
            {"newuser1", "newuser2@gmail.com", "password123", "password123", "Nguyen Van B", "0123456789", "HCMC", false, "Tên đăng nhập đã tồn tại", "TC_REGISTER_002"},
            {"newuser3", "newuser1@gmail.com", "password123", "password123", "Nguyen Van E", "0123456789", "Can Tho", false, "Email đã tồn tại", "TC_REGISTER_003"},
            {"newuser4", "newuser12@gmail.com", "password123", "password456", "Nguyen Van L", "0123456789", "Hue", false, "Mật khẩu nhập lại không khớp", "TC_REGISTER_004"}
        };
    }
    @AfterEach
    public void teareach() {
    	WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
        logoutButton.click();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
