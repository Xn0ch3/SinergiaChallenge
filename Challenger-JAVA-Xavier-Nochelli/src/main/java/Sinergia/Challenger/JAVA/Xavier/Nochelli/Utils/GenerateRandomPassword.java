package Sinergia.Challenger.JAVA.Xavier.Nochelli.Utils;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.PasswordGenerator;

import java.security.SecureRandom;

public class GenerateRandomPassword {

    private static int MIN_PASSWORD_LENGTH = 8;

    private static int MAX_PASSWORD_LENGTH = 16;

    private static String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

    public static String generateNewPassword(){

        SecureRandom random = new SecureRandom();

        int passwordLength = MIN_PASSWORD_LENGTH + random.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH +1);

        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++){
            int index = random.nextInt(validChars.length());
            password.append(validChars.charAt(index));
        }
        return password.toString();

    }

}
