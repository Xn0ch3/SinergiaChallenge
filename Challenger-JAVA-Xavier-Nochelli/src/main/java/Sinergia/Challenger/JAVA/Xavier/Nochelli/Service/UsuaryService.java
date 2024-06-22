package Sinergia.Challenger.JAVA.Xavier.Nochelli.Service;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.CreateUsuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO.UsuaryDTO;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuaryService {

    Usuary getUserByEmail(String email);

    void changePassword(String email, String currentPassword, String newPassword);

    Usuary findByEmail (String Email);

    List<UsuaryDTO> getAllUsuaryDTO();

    List<Usuary> getAllUsuary();

    ResponseEntity<String> CreateUsuary(CreateUsuary createUsuary);

    boolean existByEmail(String email);

    void saveUsuary(Usuary usuary);

}
