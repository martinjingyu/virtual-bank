package Test.Testutill;

import org.junit.jupiter.api.Test;
import utill.validate.Validate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ValidateTest class provides unit tests for the Validate class.
 */
public class ValidateTest {


    /**
     * Tests the validateDescription method.
     * Verifies various scenarios for description validation.
     */
    @Test
    public void testValidateDescription() {
        // Valid descriptions
        assertTrue(Validate.validateDescription("This is a valid description."));
        assertTrue(Validate.validateDescription("Another description, which is also valid!"));
        assertTrue(Validate.validateDescription("Short description?"));
        assertTrue(Validate.validateDescription("")); // Empty description

        // Invalid descriptions
        assertFalse(Validate.validateDescription(null)); // Null description
        assertFalse(Validate.validateDescription("A".repeat(151))); // Description too long
        assertFalse(Validate.validateDescription("Invalid@description#")); // Description with invalid characters
    }

    /**
     * Tests the validateSalary method.
     * Verifies various scenarios for salary validation.
     */
    @Test
    public void testValidateSalary() {
        // Valid salaries
        assertTrue(Validate.validateSalary("123"));
        assertTrue(Validate.validateSalary("123.45"));
        assertTrue(Validate.validateSalary("0.99"));
        assertTrue(Validate.validateSalary("1234567890.12"));

        // Invalid salaries
        assertFalse(Validate.validateSalary(null));  // null case
        assertFalse(Validate.validateSalary(""));  // empty string
        assertFalse(Validate.validateSalary("."));  // single decimal point
        assertFalse(Validate.validateSalary(".99"));  // no digit before decimal
        assertFalse(Validate.validateSalary("123."));  // no digit after decimal
        assertFalse(Validate.validateSalary("123.456"));  // more than two decimal places
        assertFalse(Validate.validateSalary("abc"));  // non-digit characters
        assertFalse(Validate.validateSalary("123.45.67"));  // multiple decimal points
        assertFalse(Validate.validateSalary("123a45"));  // letters in the string
    }
}
