package poly.edu;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestDemo {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, -1})
    void testIsPositive(int number) {
        assertTrue(number > 0, "Number should be positive");
    }

    @Test
    void testWithAssertAll() {
        assertAll("Group of assertions",
                () -> assertTrue(5 > 0, "5 should be positive"),
                () -> assertTrue(10 > 0, "10 should be positive"),
                () -> assertTrue(-1 < 0, "-1 should be negative") // Kiểm tra số âm
        );
    }
}
