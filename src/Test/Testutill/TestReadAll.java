/**
 * Tests the initialization of the utility classes by checking various conditions.
 */
package Test.Testutill;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import utill.read.*;

public class TestReadAll {
    @Test
    void testInitialize() {
        // Check the validity of children's secret
        assertEquals(true, CheckChildrenSecret.checkChildrenSecret("1", "2", "data/secret.txt"));
        assertEquals(true, CheckChildrenSecret.checkChildrenSecret("222", "3333", "data/secret.txt"));
        assertEquals(false, CheckChildrenSecret.checkChildrenSecret("1", "3", "data/secret.txt"));
        // Check the validity of ID
        assertEquals(false, CheckID.checkID("1", "data/secret.txt"));
        assertEquals(false, CheckID.checkID("3", "data/secret.txt"));
        assertEquals(true, CheckID.checkID("123", "data/secret.txt"));
        // Check the validity of parent's secret
        assertEquals(true, CheckParentSecret.checkParentSecret("1", "3", "data/secret.txt"));
        assertEquals(true, CheckParentSecret.checkParentSecret("222", "3", "data/secret.txt"));
        assertEquals(false, CheckParentSecret.checkParentSecret("222", "3333", "data/secret.txt"));
    }

}
