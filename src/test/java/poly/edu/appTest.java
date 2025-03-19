package poly.edu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import poly.edu.Entity.app;

public class appTest {

    @Test
    public void testIsEvenNumber2() {
        app tempApp = new app();
        boolean result = tempApp.isEvenNum(2);
        assertTrue(result, "2 phải là số chẵn");
    }

    @Test
    public void testIsEvenNumber3() {
        app tempApp = new app();
        boolean result = tempApp.isEvenNum(3);
        assertFalse(result, "3 là số lẻ nên phải trả về false");
    }

    @Test
    public void testIsEvenNumber4() {
        app tempApp = new app();
        boolean result = tempApp.isEvenNum(4);
        assertTrue(result, "4 phải là số chẵn");
    }
}
