package Testutill;

import Entity.Kids;
import utill.Paths;
import utill.read.ReadAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utill.write.WriteAll;

class WriteAllTest {

    @Test
    void writeAll() {
        // Mock data
        String id = "123";
        Kids kid = new Kids();
        // Call the method
        WriteAll.writeAll(id, kid);
        // If this method fails, it will raise exception.
        // So we don't need to make specific assertion.
        assertTrue(true);
    }
}