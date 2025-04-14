//package poly.edu;
//
//import static org.testng.Assert.assertEquals;
//
//import java.io.*;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import poly.edu.Controller.LoginController;
//import poly.edu.Repository.UserRepository;
//import poly.edu.Service.CookieService;
//import poly.edu.Service.ParamService;
//import poly.edu.Service.SessionService;
//
//public class GoogleTest {
//    WebDriver driver;
//    Workbook workbook;
//    Sheet sheet;
//    int rowCount = 0;
//
//    private boolean isLoggedIn() {
//        return driver.getCurrentUrl().equals("http://localhost:8080/user/products") ||
//                isElementPresent(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
//    }
//
//    private boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private void logout() throws InterruptedException {
//        try {
//            WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(), 'Đăng xuất')]"));
//            logoutButton.click();
//            System.out.println("Đã đăng xuất thành công!");
//        } catch (Exception e) {
//            System.out.println("Không tìm thấy nút Đăng Xuất.");
//        }
//    }
//
//    private static final String FILE_PATH = "C:\\Users\\nkha7\\Documents\\workspace-spring-tool-suite-4-4.27.0.RELEASE\\PS39199_NguyenNhutKha_Assignment\\TestResults.xlsx";
//
//
//    @BeforeTest
//    public void setup() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        workbook = new XSSFWorkbook();
//        sheet = workbook.createSheet("Test Results");
//
//        // Ghi tiêu đề cột
//        Row headerRow = sheet.createRow(rowCount++);
//        String[] headers = {"Username", "Password", "Expected Result", "Actual Result", "Status"};
//        for (int i = 0; i < headers.length; i++) {
//            headerRow.createCell(i).setCellValue(headers[i]);
//        }
//    }
//
//    //	    @Test
////	    public void test1() {
////	        try {
////	            String url = "https://www.google.com"; // Đổi "http" thành "https"
////	            String title_web = "Google";
////	            driver.get(url);
////	            String title = driver.getTitle();
////
////	            if (title.equals(title_web)) {
////	                System.out.println("Test Pass");
////	            } else {
////	                System.out.println("Test Fail");
////	            }
////	        } finally {
////	            if (driver != null) {
////	                driver.quit();
////	            }
////	        }
////	    }
//    @DataProvider(name = "loginData")
//    public Object[][] loginData() {
//        return new Object[][]{
//                {"john@example.com", "abcdef123hashed", true},   // Đăng nhập thành công
//                {"michael@example.com", "mnp456hashed", true},   // Đăng nhập thành công
//                {"michael@example.com", "mnp456hashed", true},   // Đăng nhập thành công
//                {"invalidUser", "abcdef123hashed", false}, // Sai username
//                {"validUser", "invalidPass", false}, // Sai password
//                {"", "abcdef123hashed", false},            // Username rỗng
//                {"john@example.com", "", false}             // Password rỗng
//        };
//    }
//
//    //	    @Test(dataProvider = "loginData")
////	    public void testLogin(String username, String password, boolean expectedResult) {
////	        driver.get("http://localhost:8080/account/login"); // Thay thế bằng trang web thực tế
////
////	        // Nhập username
////	        WebElement usernameField = driver.findElement(By.id("email"));
////	        usernameField.sendKeys(username);
////
////	        // Nhập password
////	        WebElement passwordField = driver.findElement(By.id("password"));
////	        passwordField.sendKeys(password);
////
////	        // Nhấn nút Login
////	        WebElement loginButton = driver.findElement(By.id("btn-login"));
////	        loginButton.click();
////
////	        // Kiểm tra kết quả đăng nhập
////	        boolean loginSuccess = true;
////	        try {
////	        	driver.findElement(By.cssSelector("div.error-msg")); 
////	            driver.findElement(By.id("errorMsg")); // Nếu tìm thấy thông báo lỗi, nghĩa là đăng nhập thất bại
////	            loginSuccess = false;
////	        } catch (Exception e) {
////	            loginSuccess = true; // Nếu không tìm thấy error message, đăng nhập thành công
////	        }
////
////	        // So sánh kết quả thực tế với mong đợi
////	        assertEquals(loginSuccess, expectedResult, "Test thất bại với tài khoản: " + username);
////	    }
//    @Test(dataProvider = "loginData")
//    public void testFindElement(String username, String password, boolean expectedResult) throws FileNotFoundException, IOException, InterruptedException {
//        if (isLoggedIn()) {
//            System.out.println("Người dùng đã đăng nhập, tiến hành đăng xuất trước khi test...");
//            logout();
//        }
//        driver.get("http://localhost:8080/account/login");
//        WebElement usernameField = driver.findElement(By.id("email"));
//        usernameField.sendKeys(username);
//        WebElement passwordField = driver.findElement(By.id("password"));
//        passwordField.sendKeys(password);
//        WebElement loginButton = driver.findElement(By.id("btn-login"));
//        System.out.println(driver.getCurrentUrl());
//        loginButton.click();
//        boolean loginSuccess;
//        if (driver.getCurrentUrl().equals("http://localhost:8080/user/products")) {
//            loginSuccess = true;
//        } else {
//            loginSuccess = false;
//        }
//
//
//        try {
//            driver.findElement(By.cssSelector("div.alert-danger"));
//            loginSuccess = false;
//
//        } catch (Exception e) {
//            loginSuccess = true;
//        }
//        saveResultToExcel(username, password, expectedResult, loginSuccess);
//        assertEquals(loginSuccess, expectedResult, "Test thất bại với tài khoản: " + username);
//
//    }
//
//    public void saveResultToExcel(String username, String password, boolean expected, boolean actual) {
//        Row row = sheet.createRow(rowCount++);
//        row.createCell(0).setCellValue(username);
//        row.createCell(1).setCellValue(password);
//        row.createCell(2).setCellValue(expected ? "Pass" : "Fail");
//        row.createCell(3).setCellValue(actual ? "Pass" : "Fail");
//        row.createCell(4).setCellValue(expected == actual ? "Pass" : "Fail");
//
//        // Ghi vào file ngay sau mỗi test
//        try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) {
//            workbook.write(fileOut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @AfterTest
//    public void tearDown() {
//        try {
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        driver.quit();
//    }
//
//}
