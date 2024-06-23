package Sinergia.Challenger.JAVA.Xavier.Nochelli.Utils;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.PasswordGenerator;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class GenerateRandomPassword {

    private static int MIN_PASSWORD_LENGTH = 8;

    private static int MAX_PASSWORD_LENGTH = 16;

    private  static String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@$%&*])[A-Z a-z\\d!@$%&*]{8,}$";

    private static String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

    private static Pattern pattern = Pattern.compile(regex);

    public static String generateNewPassword(){

        SecureRandom random = new SecureRandom();

        String password;

        do {
            int passwordLength = MIN_PASSWORD_LENGTH + random.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH +1);

            StringBuilder passwordBuilder = new StringBuilder(passwordLength);

            for (int i = 0; i < passwordLength; i++){
                int index = random.nextInt(validChars.length());
                passwordBuilder.append(validChars.charAt(index));
            }
            password = passwordBuilder.toString();
        }while (!pattern.matcher(password).matches());

        return password;



    }

}
