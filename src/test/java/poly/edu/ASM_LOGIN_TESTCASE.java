package poly.edu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ASM_LOGIN_TESTCASE {
    WebDriver driver;
    Workbook workbook;
    Sheet sheet;
    int rowCount = 0;
    private static final String FILE_PATH = "C:\\Users\\nkha7\\Documents\\workspace-spring-tool-suite-4-4.27.0.RELEASE\\PS39199_NguyenNhutKha_Assignment\\TestResults.xlsx";

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/account/login");

        if (isLoggedIn()) {
            System.out.println("Người dùng đã đăng nhập, tiến hành đăng xuất trước khi test...");
            logout();
        }
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Test Results");

        // Ghi tiêu đề cột
        Row headerRow = sheet.createRow(rowCount++);
        String[] headers = {"Username", "Password", "Expected Result", "Actual Result", "Status"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean expectedResult, String expectedMessage) throws InterruptedException {
        driver.get("http://localhost:8080/account/login");

        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));

        emailField.sendKeys(email);
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(), 'Đăng Nhập')]")).click();

        if (email.isEmpty() || password.isEmpty()) {
            String emailValidation = "Vui lòng nhập thông tin";
            String passwordValidation = "Vui lòng nhập thông tin";

            System.out.println("Email Validation: " + emailValidation);
            System.out.println("Password Validation: " + passwordValidation);

            Assert.assertFalse(emailValidation.isEmpty() || passwordValidation.isEmpty(),
                    "Trình duyệt không hiển thị thông báo lỗi khi input trống!");
            return;
        }

        Thread.sleep(1000);

        boolean isLoggedIn = isLoggedIn();
        saveResultToExcel(email, password, expectedResult, isLoggedIn);
        Assert.assertEquals(isLoggedIn, expectedResult);

        if (!expectedResult) {
            WebElement errorElement = driver.findElement(By.className("alert-danger"));
            String actualMessage = errorElement.getText().trim();
            System.out.println("Thông báo lỗi nhận được: " + actualMessage);
            Assert.assertEquals(actualMessage, expectedMessage);
        }

        if (isLoggedIn) {
            logout();
        }
    }
    public void saveResultToExcel(String username, String password, boolean expected, boolean actual) {
        Row row = sheet.createRow(rowCount++);
        row.createCell(0).setCellValue(username);
        row.createCell(1).setCellValue(password);
        row.createCell(2).setCellValue(expected ? "Pass" : "Fail");
        row.createCell(3).setCellValue(actual ? "Pass" : "Fail");
        row.createCell(4).setCellValue(expected == actual ? "Pass" : "Fail");

        // Ghi vào file ngay sau mỗi test
        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRememberMe() throws InterruptedException {
        driver.get("http://localhost:8080/account/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement rememberCheckbox = driver.findElement(By.id("rememberMe"));

        emailField.sendKeys("john@example.com");
        passwordField.sendKeys("abcdef123hashed");
        if (!rememberCheckbox.isSelected()) {
            rememberCheckbox.click();
        }
        driver.findElement(By.xpath("//button[contains(text(), 'Đăng Nhập')]")).click();
        Thread.sleep(1000);
        boolean isLoggedIn = isLoggedIn();
        Assert.assertTrue(isLoggedIn, "Đăng nhập không thành công!");
        driver.quit();
        Thread.sleep(2000);
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/user/order");
        boolean stillLoggedIn = !driver.getCurrentUrl().contains("/user/order");
        Assert.assertTrue(stillLoggedIn, "Remember Me không hoạt động!");
        logout();
    }
    // Kiểm tra xem người dùng có đang đăng nhập hay không
    private boolean isLoggedIn() {
        return driver.getCurrentUrl().equals("http://localhost:8080/user/products") ||
                isElementPresent(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
    }

    // Đăng xuất nếu đang đăng nhập
    private void logout() throws InterruptedException {
        try {
            WebElement logoutButton = driver.findElement(By.linkText("Đăng xuất"));
            logoutButton.click();
            System.out.println("Đã đăng xuất thành công!");
        } catch (Exception e) {
            System.out.println("Không tìm thấy nút Đăng Xuất.");
        }
    }

    // Kiểm tra xem phần tử có tồn tại trên trang hay không
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
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


    @AfterClass
    public void tearDown() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}