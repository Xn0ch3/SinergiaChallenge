package Sinergia.Challenger.JAVA.Xavier.Nochelli.Security;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Repository.UsuaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    //Se inyecta la Interfaz que se comunica con la DB,
    @Autowired
    UsuaryRepository usuaryRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {
            //Es una variable de Cliente, que devuelve un cliente mediante la búsqueda de Email, en UsuaryRepositories.
            Usuary usuary = usuaryRepository.findByEmail(inputName);

            if (usuary != null) {
                //Iniciamos Session con Email y password.
                return new User(usuary.getEmail(), usuary.getPassword(),
                        AuthorityUtils.createAuthorityList(usuary.getRoleType().toString()));
                //AuthorityUtils: en Sprint Security proporciona metodos de utilidad para trabajar
                //con roles y autoridades en contexto de seguridad,

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }

    //PasswordEncoder se encarga de Encriptar las contraseñas de los Usuarios
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }



}
