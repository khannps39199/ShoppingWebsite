//package poly.edu;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class Lifecycle_Junit5 {
//
//
//    @BeforeAll
//    static void initAll() {
//        System.out.println("Before All - Run before all methods once");
//    }
//
//
//    @BeforeEach
//    void init() {
//        System.out.println("Before Each - Run before each test method");
//    }
//
//    @DisplayName("First Test")
//    @Test
//    void testMethod() {
//        System.out.println("Test Method 1");
//    }
//
//    @Test
//    @Disabled
//    void testMethod2() {
//        System.out.println("Test Method 2");
//    }
//
//    // Một phương thức kiểm thử bình thường
//    @Test
//    void testMethod3() {
//        System.out.println("Test Method 3");
//    }
//
//    // @AfterEach là phương thức được gọi sau mỗi bài test
//    // Đây là nơi bạn có thể dọn dẹp tài nguyên hoặc đối tượng sử dụng trong bài test
//    @AfterEach
//    void tearDown() {
//        System.out.println("After Each - Run after each test method");
//    }
//
//    // @AfterAll là phương thức chạy một lần sau tất cả các bài kiểm tra (phải là static)
//    @AfterAll
//    static void tearDownAll() {
//        System.out.println("After All - Run after all tests once");
//    }
//}
