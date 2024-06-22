package Sinergia.Challenger.JAVA.Xavier.Nochelli.DTO;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.RoleType;

public class CreateUsuary {

    private String nombre, apellido, email , password;

    private RoleType roleType = RoleType.USUARY;

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
