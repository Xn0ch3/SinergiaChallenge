package Sinergia.Challenger.JAVA.Xavier.Nochelli.Models;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class PasswordGenerator {

    private final PasswordEncoder passwordEncoder;
    private final Random random = new SecureRandom();

    public PasswordGenerator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String generateRandomPassword() {
        // Longitud de la contraseña generada
        int length = 12;

        // Caracteres válidos para la contraseña (puedes ajustar según tus requisitos)
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

        // Crear un StringBuilder para construir la contraseña aleatoria
        StringBuilder sb = new StringBuilder(length);

        // Generar la contraseña aleatoria
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            sb.append(validChars.charAt(index));
        }

        // Codificar la contraseña generada utilizando PasswordEncoder
        String rawPassword = sb.toString();
        return passwordEncoder.encode(rawPassword);
    }
}
