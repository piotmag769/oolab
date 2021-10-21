import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {

    @Test
    void test_toString()
    {
        Vector2d v = new Vector2d(1, 5);
        assertEquals(v.toString(), "(1,5)");
    }
}
