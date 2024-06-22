package Sinergia.Challenger.JAVA.Xavier.Nochelli.Models;

import jakarta.persistence.*;

@Entity
public class Usuary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nombre, apellido, email , password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USUARY;

    //Constructor Sin Parametros.
    public Usuary() {
    }

    //Constructor Parametrizado.

    public Usuary(String nombre, String apellido, String email, String password, RoleType roleType) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }


    //Metodos Accesores.

    public Long getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }


    //ToString.


    @Override
    public String toString() {
        return "Usuary{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
