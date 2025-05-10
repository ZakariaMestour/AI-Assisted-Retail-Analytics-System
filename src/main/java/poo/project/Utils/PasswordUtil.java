package poo.project.Utils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordUtil {

    public  String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+<>?";
        String allChars = upper + lower + digits + symbols;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        return password.toString();
    }


}
