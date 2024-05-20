package utill.validate;

public class Validate {
    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        boolean hasLetter = false;
        for (char c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return false;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        return hasLetter;
    }


    // 检查description输入
    public static boolean validateDescription(String description) {
        if (description == null || description.length() > 150) {
            return false;
        }

        for (char c : description.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c != '.' && c != ',' && c != '!' && c != '?') {
                return false;
            }
        }
        return true;
    }

    // 检查salary输入
    public static boolean validateSalary(String salary) {
        if (salary == null || salary.isEmpty()) {
            return false;
        }

        boolean hasDecimalPoint = false;
        boolean hasDigitBeforeDecimal = false;

        for (int i = 0; i < salary.length(); i++) {
            char c = salary.charAt(i);
            if (c == '.') {
                if (hasDecimalPoint) {
                    return false; // 多个小数点
                }
                hasDecimalPoint = true;
                if (i == 0 || !Character.isDigit(salary.charAt(i - 1))) {
                    return false; // 小数点前必须有数字
                }
            } else if (!Character.isDigit(c)) {
                return false; // 不是数字
            } else {
                hasDigitBeforeDecimal = true;
            }
        }

        return hasDigitBeforeDecimal;
    }
}
