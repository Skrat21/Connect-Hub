public class EmailFormatChecker {

    public static boolean isValidEmail(String email) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        // Check if there is exactly one "@" symbol
        int atIndex = email.indexOf('@');
        if (atIndex == -1 || email.indexOf('@', atIndex + 1) != -1) {
            return false; // No "@" or more than one "@" found
        }

        // Check if there is text before "@" and after "@"
        if (atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        // Check if there is a dot after "@" and some text between "@" and the dot
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == atIndex + 1 || dotIndex == email.length() - 1) {
            return false;
        }

        // Ensure there is no consecutive dots in the domain part
        if (email.substring(atIndex + 1).contains("..")) {
            return false;
        }

        return true;

        }}
