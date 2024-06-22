package Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.ServiceImplement;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.CreateUsuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.UsuaryDTO;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Repository.UsuaryRepository;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.EmailService;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.UsuaryService;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Utils.GenerateRandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuaryServiceImplement implements UsuaryService {

    @Autowired
    private UsuaryRepository usuaryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private EmailService emailService; // Suponiendo que tienes un servicio para enviar correos electrónicos


    @Override
    public Usuary getUserByEmail(String email) {
        return usuaryRepository.findByEmail(email);
    }

    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        Usuary usuary =getUserByEmail(email);

        // Codificar y establecer la nueva contraseña
        String encodedPassword = passwordEncoder.encode(newPassword);
        usuary.setPassword(encodedPassword);
        usuaryRepository.save(usuary);
    }

    @Override
    public Usuary findByEmail(String email) {
        return usuaryRepository.findByEmail(email);
    }

    @Override
    public List<UsuaryDTO> getAllUsuaryDTO() {
        return getAllUsuary().stream().map(usuary -> new UsuaryDTO(usuary)).collect(Collectors.toList());
    }

    @Override
    public List<Usuary> getAllUsuary() {
        return usuaryRepository.findAll();
    }

    @Override
    public ResponseEntity<String> CreateUsuary(CreateUsuary createUsuary) {
        if (createUsuary.getNombre().isBlank()){
            return new ResponseEntity<>("El Nombre no puede estar vacío.", HttpStatus.FORBIDDEN);
        }
        if (createUsuary.getApellido().isBlank()){
            return new ResponseEntity<>("El Apellido no puede estar vacío.", HttpStatus.FORBIDDEN);
        }
        if (createUsuary.getEmail().isBlank()){
            return new ResponseEntity<>("Tiene que ingresar un Email.", HttpStatus.FORBIDDEN);
        }
        if (createUsuary.getPassword().isBlank()){
            return new ResponseEntity<>("Tiene que ingresar un Password", HttpStatus.FORBIDDEN);
        }

        if (existByEmail(createUsuary.getEmail())){
            return new ResponseEntity<>("El Email ya esta registrado.", HttpStatus.FORBIDDEN);
        }
        Usuary usuary = new Usuary(createUsuary.getNombre(), createUsuary.getApellido(), createUsuary.getEmail(), passwordEncoder.encode(passwordValidator(createUsuary.getPassword())), createUsuary.getRoleType());
        usuaryRepository.save(usuary);

        return new ResponseEntity<>("Cliente Creado con Exito", HttpStatus.CREATED);

    }

    @Override
    public boolean existByEmail(String Email) {
        return usuaryRepository.existsByEmail(Email);
    }

    @Override
    public void saveUsuary(Usuary usuary) {
        usuaryRepository.save(usuary);
    }

    public String passwordValidator(String password) throws IllegalArgumentException{

        String pass = password;

        if(!pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%&*])[A-Z a-z\\d!@#$%&*]{8,}$")){
            throw new IllegalArgumentException("password invalid");
        }
        return pass;
    }

    @Override
    public ResponseEntity<String> forgotPassword(String email) {
        try {
            // Generar una nueva contraseña aleatoria
            String newPassword = GenerateRandomPassword.generateNewPassword();

            // Cambiar la contraseña del usuario
            changePassword(email, null, newPassword);

            // Enviar la nueva contraseña por correo electrónico
            String subject = "Recuperación de Contraseña";
            String text = "Tu nueva contraseña es: " + newPassword;
            emailService.sendSimpleEmail(email, subject, text);

            return ResponseEntity.ok("Se ha enviado una nueva contraseña al correo electrónico proporcionado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

}
