package Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.RoleType;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;

public class UsuaryDTO {

    private Long Id;

    private String nombre, apellido, email , password;

    private RoleType roleType = RoleType.USUARY;

    //Constructor parametrizado.
    public UsuaryDTO(Usuary usuary) {
        Id = usuary.getId();
        nombre = usuary.getNombre();
        apellido = usuary.getApellido();
        email = usuary.getEmail();
        password = usuary.getPassword();
        roleType = usuary.getRoleType();
    }

    //Getters


    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}
