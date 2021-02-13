package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean email(String email) {
        Pattern pattern = Pattern.compile("^[a-z0-9](?!.*?[^\\na-z0-9]{2})[^\\s@]+@[^\\s@]+\\.[^\\s@]+[a-z0-9]$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
