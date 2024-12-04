public class isPasswordValid {

    public static boolean isPasswordValid(String password) {
        //the password must be more than 8 characters
        if(password.length() < 8) return false;
        //regex: checks if at least one character is uppercase letter
        if(!password.matches(".*[A-Z].*")) return false;
        //regex: checks if at least one character is lawercase letter
        if(!password.matches(".*[a-z].*")) return false;
        //regex: checks if at least one character is digit
        if(!password.matches(".*[0-9].*")) return false;
        //regex: checks if at least one character is spicial character including ()
        if(!password.matches(".*[(|+*/=_*&^%$#@!`~.')].*")) return false;
       
        return true;
    }
}
