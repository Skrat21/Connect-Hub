public class PasswordFormatChecker {

    public static boolean isPasswordLengthValid(String password) {
        //the password must be more than 8 characters
            return (password.length() < 8);
        }
    public static boolean isPasswordUppercaseValid(String password) {
        //regex: checks if at least one character is uppercase letter
            return (!password.matches(".*[A-Z].*"));
        }
    public static boolean isPasswordLawercaseValid(String password){
        //regex: checks if at least one character is lawercase letter
            return (!password.matches(".*[a-z].*"));
        }
    public static boolean isPasswordDigitValid(String password){
        //regex: checks if at least one character is digit
            return (!password.matches(".*[0-9].*"));
        }
    public static boolean isPasswordSpicialCharacterValid(String password){
        //regex: checks if at least one character is spicial character including ()
            return (!password.matches(".*[(|+*/=_*&^%$#@!`~.')].*"));
        }
    public static boolean isPasswordValid(String password){
        return (isPasswordLengthValid(password)
                && isPasswordUppercaseValid(password)
                &&isPasswordLawercaseValid(password)
                &&isPasswordDigitValid(password)
                && isPasswordSpicialCharacterValid(password));
    }
}
