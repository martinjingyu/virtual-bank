package Test.Testutill;

import org.junit.Test;
import utill.validate.Validate;

import static org.junit.Assert.*;

public class ValidateNameTest {

    @Test
    public void testValidName() {
        String validName = "John Doe";
        try {
            assertEquals(validName,Validate.validateName(validName));
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testEmptyName() throws Exception {
        String emptyName = "";
        Validate.validateName(emptyName);
    }

    @Test(expected = Exception.class)
    public void testNullName() throws Exception {
        String nullName = null;
        Validate.validateName(nullName);
    }

    @Test(expected = Exception.class)
    public void testInvalidCharacter() throws Exception {
        String invalidName = "John@Doe";
        Validate.validateName(invalidName);
    }

    @Test(expected = Exception.class)
    public void testNoLetter() throws Exception {
        String noLetterName = "12345";
        Validate.validateName(noLetterName);
    }
}
