package Test.Testutill;

import utill.validate.Validate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {

    @Test
    void validateName_validName() throws Exception {
        String name = "John Doe";
        assertEquals(name, Validate.validateName(name));
    }

    @Test
    void validateName_emptyName() {
        String name = "";
        Exception exception = assertThrows(Exception.class, () -> Validate.validateName(name));
        assertEquals("Invalid name: name cannot be empty.", exception.getMessage());
    }

    @Test
    void validateName_nullName() {
        String name = null;
        Exception exception = assertThrows(Exception.class, () -> Validate.validateName(name));
        assertEquals("Invalid name: name cannot be empty.", exception.getMessage());
    }

    @Test
    void validateName_invalidCharacters() {
        String name = "John@Doe";
        Exception exception = assertThrows(Exception.class, () -> Validate.validateName(name));
        assertEquals("Invalid name: contains invalid characters.", exception.getMessage());
    }

    @Test
    void validateName_noLetters() {
        String name = "12345";
        Exception exception = assertThrows(Exception.class, () -> Validate.validateName(name));
        assertEquals("Invalid name: must contain at least one letter.", exception.getMessage());
    }

    @Test
    void validateName_lastCharacterSpace() {
        String name = "John ";
        Exception exception = assertThrows(Exception.class, () -> Validate.validateName(name));
        assertEquals("The last character can't be a space. ", exception.getMessage());
    }

    @Test
    void validateDescription_validDescription() {
        String description = "This is a valid description.";
        assertTrue(Validate.validateDescription(description));
    }

    @Test
    void validateDescription_nullDescription() {
        String description = null;
        assertFalse(Validate.validateDescription(description));
    }

    @Test
    void validateDescription_tooLongDescription() {
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 151; i++) {
            description.append("a");
        }
        assertFalse(Validate.validateDescription(description.toString()));
    }

    @Test
    void validateDescription_invalidCharacters() {
        String description = "Invalid description #!";
        assertFalse(Validate.validateDescription(description));
    }

    @Test
    void validateSalary_validSalary() {
        String salary = "12345.67";
        assertTrue(Validate.validateSalary(salary));
    }

    @Test
    void validateSalary_invalidSalary() {
        String salary = "12.345";
        assertFalse(Validate.validateSalary(salary));
    }

    @Test
    void validateSalary_nullSalary() {
        String salary = null;
        assertFalse(Validate.validateSalary(salary));
    }

    @Test
    void validateSalary_emptySalary() {
        String salary = "";
        assertFalse(Validate.validateSalary(salary));
    }

    @Test
    void validateNumber_validNumber() throws Exception {
        String input = "123.45";
        assertEquals(123.45, Validate.validateNumber(input));
    }

    @Test
    void validateNumber_invalidNumber() {
        String input = "123.456";
        Exception exception = assertThrows(Exception.class, () -> Validate.validateNumber(input));
        assertEquals("invalid number", exception.getMessage());
    }

    @Test
    void validateRepeat_noRepeat() throws Exception {
        List<String> accountList = new ArrayList<>();
        accountList.add("Account1");
        assertTrue(Validate.validateRepeat("Account2", accountList));
    }

    @Test
    void validateRepeat_repeated() {
        List<String> accountList = new ArrayList<>();
        accountList.add("Account1");
        Exception exception = assertThrows(Exception.class, () -> Validate.validateRepeat("Account1", accountList));
    }

    @Test
    void validateInterest_validInterest() throws Exception {
        String interest = "5.25";
        assertEquals(5.25, Validate.validateInterest(interest));
    }

}

