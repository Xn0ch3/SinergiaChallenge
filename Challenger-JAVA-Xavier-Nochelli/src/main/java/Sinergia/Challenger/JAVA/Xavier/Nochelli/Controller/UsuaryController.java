package Sinergia.Challenger.JAVA.Xavier.Nochelli.Controller;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.CreateUsuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.UsuaryDTO;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.EmailService;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Service.UsuaryService;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Utils.GenerateRandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuary")
public class UsuaryController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuaryService usuaryService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/list")
    public List<UsuaryDTO> GetAllUsuaryDTO(){
      return usuaryService.getAllUsuaryDTO();
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateUsuary(@RequestBody CreateUsuary createUsuary){
        return usuaryService.CreateUsuary(createUsuary);
    }

    @GetMapping("/current")
    public ResponseEntity<Object> getOneClient(Authentication authentication) {
        Usuary usuary = usuaryService.findByEmail(authentication.getName());
        //Cuando este se realiza la autenticacion y es exitosa, se inicia la session generandose un nuevo TOKKEN
        UsuaryDTO usuaryDTO = new UsuaryDTO(usuary);
        return new ResponseEntity<>(usuaryDTO, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return usuaryService.forgotPassword(email);
    }
}
